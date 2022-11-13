const DashboardLogo = () => {
    return (
        <a href={"/dashboard"}>
            <img
                src="https://cdn.renfei.net/Logo/RF_white.svg"
                alt="后台管理系统"
                width={30}
                style={{ verticalAlign: 'middle' }}
            />
            <span style={{ padding: '0 0 0 10px' }}>后台管理系统</span>
        </a>
    )
}

export default DashboardLogo