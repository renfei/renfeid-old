import Head from 'next/head'
import DashboardLayout from "../../components/layout/dashboard";

const Dashboard = () => {
    return (
        <>
            <div>
                <Head>
                    <title>控制台</title>
                </Head>

                <main>
                    sdf
                </main>
            </div>
        </>
    )
}

Dashboard.getLayout = (page: any) => {
    return (
        <DashboardLayout>
            {page}
        </DashboardLayout>
    )
}

export default Dashboard