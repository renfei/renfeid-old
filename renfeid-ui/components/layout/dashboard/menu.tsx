import React from 'react';
import {useRouter} from 'next/router'
import {Layout, Menu} from 'antd';
import {LaptopOutlined, NotificationOutlined, UserOutlined} from '@ant-design/icons';
import type {MenuProps} from 'antd';

const {Sider} = Layout

const items2: MenuProps['items'] = [UserOutlined, LaptopOutlined, NotificationOutlined].map(
    (icon, index) => {
        const key = String(index + 1);

        return {
            key: `sub${key}`,
            icon: React.createElement(icon),
            label: `subnav ${key}`,

            children: new Array(4).fill(null).map((_, j) => {
                const subKey = index * 4 + j + 1;
                return {
                    key: subKey,
                    label: `option${subKey}`,
                };
            }),
        };
    },
);

const menu = [
    {
        key: '/dashboard',
        label: '控制面板',
    },
    {
        key: '/dashboard/cms',
        label: '内容管理系统',
        children: [
            {
                key: '/dashboard/cms/posts',
                label: '内容列表',
            }
        ]
    }
]

const DashboardMenu = () => {
    const router = useRouter()
    return (
        <>
            <Sider width={200} style={{background: '#ffffff'}}>
                <Menu
                    mode="inline"
                    style={{height: '100%', borderRight: 0}}
                    items={menu}
                    onClick={({item, key, keyPath, domEvent}: any) => {
                        router.push(key)
                    }}
                />
            </Sider>
        </>
    )
}

export default DashboardMenu