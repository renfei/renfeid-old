import {useRouter} from 'next/router'
import Layout from "../../components/layout";

const PostPage = () => {
    const router = useRouter()
    const {id} = router.query

    return (
        <p>Post: {id}</p>
    )
}

export const getServerSideProps = async (context: any) => {
    return {
        props: {}
    }
}

PostPage.getLayout = (page: any) => {
    return (
        <Layout>
            {page}
        </Layout>
    )
}

export default PostPage
