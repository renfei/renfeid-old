import { Breadcrumb } from 'antd'
import { PageHeader } from '@ant-design/pro-layout'

const itemRender = (route: any, params: any, routes: any, paths: any) => {
    return (<a href={route.path}>{route.breadcrumbName}</a>)
}

const DashPageHeader = (props: any) => {
    return (
        <PageHeader
            className="site-page-header"
            title={props.title}
            breadcrumb={<Breadcrumb itemRender={itemRender} routes={props.routes} />}
            subTitle={props.subTitle}
        />
    )
}

export default DashPageHeader