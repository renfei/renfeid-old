#!/usr/bin/env python
#
# 友情链接检查扫描脚本
# pip3 install BeautifulSoup4
# python 3.7
# 引入系统类库
import sys
# 使用文档解析类库
from bs4 import BeautifulSoup
# 使用网络请求类库
import urllib.request

# 输入网址
html_doc = sys.argv[1]
# 获取请求
req = urllib.request.Request(html_doc)
# 打开页面
webpage = urllib.request.urlopen(req)
# 读取页面内容
html = webpage.read()
# 解析成文档对象
soup = BeautifulSoup(html, 'html.parser')  # 文档对象
# 计数器
count = 0
# 查找文档中所有a标签
for k in soup.find_all('a'):
    link = k.get('href')
    rel = k.get('rel')
    # 过滤没找到的
    if link is not None:
        # 查找链接
        if link.startswith('http://www.renfei.net') or link.startswith('https://www.renfei.net'):
            # 包含 nofollow 的标签
            if rel is not None and 'nofollow' in rel:
                pass
            else:
                count += 1
print(count)
