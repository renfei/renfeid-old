import Link from 'next/link'

const DashboardLogo = () => {
    return (
        <Link href={"/dashboard"}>
            <a>
                <img
                    src="https://cdn.renfei.net/Logo/RF_white.svg"
                    alt="后台管理系统"
                    width={30}
                    style={{verticalAlign: 'middle'}}
                />
                <span style={{padding: '0 0 0 10px'}}>后台管理系统</span>
            </a>
        </Link>
    )
}

export default DashboardLogo