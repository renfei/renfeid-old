import {Layout, Affix} from 'antd';
import Navbar from './navbar'
import SectionWrapper from "./section-wrapper";
import Footer from './footer'

const {Content} = Layout

const MyLayout = ({children}: any) => {
    return (
        <>
            <Layout>
                <Affix offsetTop={0}>
                    <Navbar/>
                </Affix>
                <SectionWrapper>SectionWrapper</SectionWrapper>
                <Content>{children}</Content>
                <Footer/>
            </Layout>
        </>
    )
}

export default MyLayout
