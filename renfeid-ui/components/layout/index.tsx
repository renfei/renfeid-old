import {Layout} from 'antd';
import Navbar from './navbar'
import Footer from './footer'

const {Content} = Layout

const MyLayout = ({children}: any) => {
    return (
        <>
            <Layout>
                <Navbar/>
                <Content>{children}</Content>
                <Footer/>
            </Layout>
        </>
    )
}

export default MyLayout
