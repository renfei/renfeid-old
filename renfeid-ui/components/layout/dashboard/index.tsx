import {Layout} from 'antd';
import DashboardNavbar from "./navbar";
import DashboardMenu from "./menu";

const {Content} = Layout

const DashboardLayout = ({children}: any) => {
    return (
        <>
            <Layout style={{height: '100%'}}>
                <DashboardNavbar/>
                <Layout>
                    <DashboardMenu></DashboardMenu>
                    <Layout style={{padding: '0 24px 24px'}}>
                        <Content
                            style={{
                                padding: 24,
                                margin: 0,
                                minHeight: 280,
                            }}
                        >{children}</Content>
                    </Layout>
                </Layout>
            </Layout>
        </>
    )
}

export default DashboardLayout