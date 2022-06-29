import {Breadcrumb, Layout, Menu} from 'antd';

const {Header} = Layout;

const Navbar = () => {
    const items = [
        {label: '菜单项一', key: 'item-1'}, // 菜单项务必填写 key
        {label: '菜单项二', key: 'item-2'},
        {
            label: '子菜单',
            key: 'submenu',
            children: [{label: '子菜单项', key: 'submenu-item-1'}],
        },
    ]

    return (
        <>
            <Header>
                <Menu
                    mode="horizontal"
                    items={items}
                />
            </Header>
        </>
    )
}

export default Navbar
