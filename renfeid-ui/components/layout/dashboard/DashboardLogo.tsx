import Link from 'next/link'

const DashboardLogo = () => {
    return (
        <Link href={"/dashboard"}>
            <img
                src="https://cdn.renfei.net/Logo/RF_white.svg"
                alt="后台管理系统"
                width={30}
                style={{ verticalAlign: 'middle' }}
            />
            <span style={{ padding: '0 0 0 10px' }}>后台管理系统</span>
        </Link>
    )
}

export default DashboardLogo