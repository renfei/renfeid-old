import {Layout, BackTop} from 'antd';
import Navbar from './navbar'
import SectionWrapper from "./section-wrapper";
import Footer from './footer'

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
        </>
    )
}

export default MyLayout
