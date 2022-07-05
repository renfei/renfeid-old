import {Layout, BackTop} from 'antd';
import Navbar from './navbar'
import SectionWrapper from "./section-wrapper";
import Footer from './footer'
import Script from 'next/script';

const {Content} = Layout

const MyLayout = ({children}: any) => {
    return (
        <>
            <Layout>
                <Navbar/>
                <SectionWrapper>SectionWrapper</SectionWrapper>
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
