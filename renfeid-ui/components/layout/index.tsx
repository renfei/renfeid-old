import useSWR from 'swr'
import {Layout, BackTop} from 'antd';
import Navbar from './navbar'
import SectionWrapper from "./section-wrapper";
import Footer from './footer'
import Script from 'next/script';
import UserInfo = API.UserInfo;

const {Content} = Layout


const MyLayout = ({children}: any) => {
    const {data, error} = useSWR('/api/auth/current/user', (...args) => fetch(...args).then((res) => res.json()))
    let userInfo: UserInfo = {
        emailVerified: false,
        id: -1,
        phoneVerified: false,
        registrationDate: "",
        registrationIp: "",
        secretLevel: "",
        username: "",
        uuid: ""
    }
    if (data && data.code && data.code == 200 && data.data) {
        userInfo = data.data
    }
    return (
        <>
            <Layout>
                <Navbar/>
                <SectionWrapper
                    userInfo={userInfo}
                >SectionWrapper</SectionWrapper>
                <Content>{children}</Content>
                <Footer/>
            </Layout>
            <BackTop/>
            <Script id="google-analytics" strategy="lazyOnload">
                {`
                    window.dataLayer = window.dataLayer || [];
                    function gtag(){dataLayer.push(arguments);}
                    gtag('js', new Date());
                    gtag('config', '${process.env.NEXT_PUBLIC_GOOGLE_ANALYTICS}', {
                      page_path: window.location.pathname,
                    });
                `}
            </Script>
            <Script id="baidu-tongji" strategy="lazyOnload">
                {`
                    var _hmt = _hmt || [];
                    (function() {
                      var hm = document.createElement("script");
                      hm.src = "https://hm.baidu.com/hm.js?${process.env.NEXT_PUBLIC_BAIDU_TONGJI}";
                      var s = document.getElementsByTagName("script")[0]; 
                      s.parentNode.insertBefore(hm, s);
                    })();
                `}
            </Script>
        </>
    )
}

export default MyLayout
