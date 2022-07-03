import {Layout} from 'antd';
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
        </>
    )
}

export default MyLayout
