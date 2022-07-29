import { Layout } from 'antd'
import DashboardNavbar from "./navbar"
import DashboardMenu from "./menu"

const { Content } = Layout

const DashboardLayout = ({ children }: any) => {
    return (
        <>
            <Layout style={{ minHeight: '100%' }}>
                <DashboardNavbar />
                <Layout>
                    <DashboardMenu></DashboardMenu>
                    <Layout>
                        <Content
                            style={{
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