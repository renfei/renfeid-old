import {Layout, Menu} from 'antd';
import type {MenuProps} from 'antd';

const {Header} = Layout

const items: MenuProps['items'] = ['1', '2', '3'].map(key => ({
    key,
    label: `nav ${key}`,
}));

const DashboardNavbar = () => {
    return (
        <>
            <Header className="header">
                <Menu theme="dark" mode="horizontal" defaultSelectedKeys={['2']} items={items}/>
            </Header>
        </>
    )
}

export default DashboardNavbar