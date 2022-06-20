import Vue from 'vue'
import apiEntire from '@/api'

export default ({app, $axios, store, redirect, error: nuxtError}, inject) => {
    // Change URL only for client
    if (process.client) {
        $axios.setBaseURL('http://localhost:9595')
    }
    // Change URL only for server
    if (process.server) {
        $axios.setBaseURL('http://localhost:9595')
    }
    $axios.defaults.timeout = 5000

    // 请求拦截
    $axios.onRequest((config) => {
        // 请求头中插入 token
        // const token = store.state.user.token
        // if (token) config.headers.token = token
    })

    // 服务器返回异常拦截
    $axios.onError((error) => {
        if (error.response) {
            // 请求已发出，且服务器的响应状态码超出了 2xx 范围
            if (error.response.status === 401) {
                redirect("/auth/signIn");
            } else {
                nuxtError({
                    statusCode: error.response.status,
                    message: error.response.data.message,
                });
            }
        } else if (error.request) {
            // 请求已发出，但没有接收到任何响应
            nuxtError({
                statusCode: 503,
                message: "后端服务暂时处于超负载或正在停机维护，无法处理请求",
            });
        } else {
            // 引发请求错误的错误信息
            nuxtError({
                statusCode: 500,
                message: "未知的错误",
            });
        }
        return error
    })

    // 接口数据返回拦截
    $axios.onResponse((response) => {
        if (response.status === 200) {
            if (response.data.code === 401) {
                redirect("/auth/signIn");
            } else if (response.data.code !== 200) {
                nuxtError({
                    statusCode: response.data.code,
                    message: response.data.code.message,
                });
            }
        }
        return response
    })

    const API = {}
    for (const i in apiEntire) {
        // 调用api时候的参数
        // 第一位为 headers参数 第二位为 post参数 第三位为get参数
        API[i] = function (headers = {}, data = '', params = '') {
            const {url, method} = {...apiEntire[i]}
            return $axios({
                url,
                method,
                headers,
                data,
                params
            })
        }
    }
    app.api = API
    inject('api', API)
}