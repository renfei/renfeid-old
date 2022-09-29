import Head from 'next/head'
import Link from 'next/link'
import nookies from 'nookies'
import Image from 'next/image'
import React, { useState, useEffect } from 'react'
import { GetServerSideProps, InferGetServerSidePropsType } from 'next'
import { Typography, Divider, Row, Col, Input, Select, Button, List, Space, message } from 'antd'
import { LikeOutlined, MessageOutlined, EyeFilled } from '@ant-design/icons'
import Layout from "../components/layout"
import * as api from '../services/api'
import APIResult = API.APIResult
import ListData = API.ListData
import SearchItem = API.SearchItem
import HotSearch = API.HotSearch
import { convertToHeaders } from '../utils/request'

const IconText = ({ icon, text }: { icon: React.FC; text: string }) => (
  <Space>
    {React.createElement(icon)}
    {text}
  </Space>
)

export const getServerSideProps: GetServerSideProps = async (context: any) => {
  const accessToken = nookies.get(context)['accessToken']
  const w: string | undefined = context.query.w
  const type: string | undefined = context.query.type
  const p: string | undefined = context.query.p
  let searchItem: APIResult<ListData<SearchItem>> | undefined
  let hotSearch: APIResult<HotSearch[]> | undefined
  if (w) {
    searchItem = await api.search(convertToHeaders(context.req.headers, context.req.socket.remoteAddress), w, type, p, accessToken)
  } else {
    hotSearch = await api.queryHotSearchList(convertToHeaders(context.req.headers, context.req.socket.remoteAddress), '15', accessToken)
  }
  return {
    props: {
      data: {
        w: w || null,
        type: type || null,
        searchItem: searchItem || null,
        hotSearch: hotSearch || null,
      }
    }
  }
}

const SearchPage = ({ data }: InferGetServerSidePropsType<typeof getServerSideProps>) => {
  const [word] = useState<string | undefined>(data.w)
  const [type] = useState<string | undefined>(data.type)
  const [p] = useState<string | undefined>(data.p)
  const [searchItem] = useState<APIResult<ListData<SearchItem>> | undefined>(data.searchItem)
  const [hotSearch] = useState<APIResult<HotSearch[]> | undefined>(data.hotSearch)
  const [selectType, setSelectType] = useState<string>(data.type || "ALL")
  const [searchWord, setSearchWord] = useState<string>(data.w)

  const search = () => {
    if (searchWord) {
      let url = `/search?w=${searchWord}`
      if (selectType) {
        url += `&type=${selectType}`
      }
      window.location.href = url
    } else {
      window.location.href = '/search'
    }
  }

  return (
    <>
      <Head>
        <title>{`${word ? `搜索：${word}` : '站内搜索'} - ${process.env.NEXT_PUBLIC_RENFEID_SITE_NAME}`}</title>
        <meta name="description" content={`搜索${process.env.NEXT_PUBLIC_RENFEID_SITE_NAME}`} />
        <meta name="keywords" content="任霏,博客,任霏博客,RenFei,NeilRen,技术,blog" />
        <meta name="author" content="任霏,i@renfei.net" />
        <meta name="copyright" content="CopyRight RENFEI.NET, All Rights Reserved." />
      </Head>

      <main style={{ backgroundColor: '#ffffff' }}>
        <section className={"renfeid-content"} style={{ paddingTop: '20px', paddingBottom: '20px' }}>
          {
            word ? (<>
              <Typography.Title level={2}>搜索：{word}</Typography.Title>
              <Divider />
              <Row>
                <Col xs={24} sm={16} md={18} style={{ paddingRight: '20px' }}>
                  <Input.Group compact>
                    <Select
                      style={{ width: '100px' }}
                      defaultValue={type ? type : 'ALL'}
                      onChange={(value, option) => { setSelectType(value) }}
                      size="large">
                      <Select.Option value="ALL">全部</Select.Option>
                      <Select.Option value="POSTS">文章</Select.Option>
                      <Select.Option value="DISCUZ">论坛</Select.Option>
                      <Select.Option value="WEIBO">微博</Select.Option>
                      <Select.Option value="PAGES">页面</Select.Option>
                      <Select.Option value="PHOTO">相册</Select.Option>
                      <Select.Option value="VIDEO">视频</Select.Option>
                      <Select.Option value="KITBOX">工具箱</Select.Option>
                    </Select>
                    <Input.Search
                      style={{ width: 'calc(100% - 100px)' }}
                      placeholder="搜索 renfei.net"
                      defaultValue={word}
                      allowClear
                      onChange={(event) => {
                        if (event && event.target) {
                          setSearchWord(event.target.value)
                        }
                      }}
                      enterButton={<Button type="primary" ghost onClick={search}>搜索</Button>}
                      size="large"
                    />
                  </Input.Group>
                  <Typography.Text type="secondary" style={{ fontSize: '12px' }}>
                    {`找到 ${searchItem?.data?.total || '0'} 个结果(${searchItem?.message || "0.000000"} seconds)`}
                  </Typography.Text>
                  <List
                    itemLayout="vertical"
                    size="large"
                    pagination={{
                      onChange: page => {
                        console.log(page);
                      },
                      itemRender: (page, t, originalElement,) => { return (<Link href={`?w=${word}${type ? '&type=' + type : ''}&p=${page}`}>{originalElement}</Link>) },
                      showSizeChanger: false,
                      current: p ? parseInt(p) : 1,
                      pageSize: 10,
                      total: searchItem?.data?.total || 0
                    }}
                    dataSource={searchItem?.data?.data}
                    renderItem={item => (
                      <List.Item
                        key={item.originalId}
                        extra={
                          <Image
                            width={272}
                            height={136}
                            style={{ borderRadius: '12px' }}
                            layout={"intrinsic"}
                            src={item.image}
                            alt={item.title}
                          />
                        }
                      >
                        <List.Item.Meta
                          title={<a href={item.url}>{item.title}</a>}
                          description={item.date}
                        />
                        <Typography.Paragraph ellipsis={{ rows: 3 }}>
                          {item.content}
                        </Typography.Paragraph>
                      </List.Item>
                    )}
                  />
                </Col>
                <Col xs={24} sm={8} md={6}>
                  占位
                </Col>
              </Row>
            </>) : (<>
              <Typography.Title level={2}>站内搜索</Typography.Title>
              <Divider />
              <Row>
                <Col xs={24} md={5}></Col>
                <Col xs={24} md={14}>
                  <Input.Group compact>
                    <Select
                      style={{ width: '100px' }}
                      defaultValue="ALL"
                      onChange={(value, option) => { setSelectType(value) }}
                      size="large">
                      <Select.Option value="ALL">全部</Select.Option>
                      <Select.Option value="POSTS">文章</Select.Option>
                      <Select.Option value="DISCUZ">论坛</Select.Option>
                      <Select.Option value="WEIBO">微博</Select.Option>
                      <Select.Option value="PAGES">页面</Select.Option>
                      <Select.Option value="PHOTO">相册</Select.Option>
                      <Select.Option value="VIDEO">视频</Select.Option>
                      <Select.Option value="KITBOX">工具箱</Select.Option>
                    </Select>
                    <Input.Search
                      style={{ width: 'calc(100% - 100px)' }}
                      placeholder="搜索 renfei.net"
                      allowClear
                      onChange={(event) => {
                        if (event && event.target) {
                          setSearchWord(event.target.value)
                        }
                      }}
                      enterButton={<Button type="primary" ghost onClick={search}>搜索</Button>}
                      size="large"
                    />
                  </Input.Group>
                  <div style={{ border: '1px solid rgba(0,0,0,0.06)', borderRadius: '5px', padding: '20px', marginTop: '10px' }}>
                    <Typography.Title level={5} style={{ marginBottom: '0' }}>搜索热榜</Typography.Title>
                    <Typography.Text type="secondary" style={{ fontSize: '12px' }}>搜索词来自网友历史搜索自动产生，不代表本站立场。</Typography.Text>
                    <ol>
                      {
                        hotSearch && hotSearch.data ? (
                          hotSearch.data.map((hot: HotSearch) => (
                            <li key={hot.word}>
                              <Link href={`/search?w=${hot.word}`}>
                                <a target={'_blank'}>{hot.word}</a>
                              </Link>
                            </li>
                          ))
                        ) : ''
                      }
                    </ol>
                  </div>
                </Col>
                <Col xs={24} md={5}></Col>
              </Row>
            </>)
          }
        </section>
      </main>
    </>
  )
}

SearchPage.getLayout = (page: any) => {
  return (
    <Layout>
      {page}
    </Layout>
  )
}

export default SearchPage