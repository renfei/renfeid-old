import Head from 'next/head'
import { GetServerSideProps, InferGetServerSidePropsType } from 'next'
import DashboardLayout from "../../components/layout/dashboard"
import CheckSignInStatus from '../../utils/CheckSignInStatus'
import UserInfo = API.UserInfo

export const getServerSideProps: GetServerSideProps = async (context: any) => {
    const userInfo: UserInfo | null = await CheckSignInStatus(context)
    if (userInfo) {
        return {
            props: {
                data: {
                    userInfo: userInfo
                }
            }
        }
    } else {
        return {
            redirect: {
                destination: '/auth/signIn?redirect=' + context.req.url,
                permanent: false,
            },
        }
    }
}

const Dashboard = ({ data }: InferGetServerSidePropsType<typeof getServerSideProps>) => {
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