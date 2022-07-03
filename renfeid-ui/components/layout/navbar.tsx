import Link from 'next/link';
import Image from 'next/image';
import {Affix} from 'antd';
import TopBarWrapper from "./top-bar-wrapper";
import {SearchOutlined, UnorderedListOutlined} from '@ant-design/icons';

const Navbar = () => {
    return (
        <>
            <TopBarWrapper>
                TopBarWrapper
            </TopBarWrapper>
            <Affix offsetTop={0}>
                <nav className={"renfeid-nav"}>
                    <div className={"renfeid-content"}>
                        <ul className={"nav-menu-mobile"}>
                            <li>
                                <UnorderedListOutlined/>
                            </li>
                            <li>
                                <Link href={"/"}>
                                    <Image
                                        className={"nav-logo"}
                                        src="https://cdn.renfei.net/Logo/RF_white.svg"
                                        alt={""}
                                        height={30}
                                        width={30}
                                    />
                                </Link>
                            </li>
                            <li><SearchOutlined/></li>
                        </ul>
                        <ul className={"nav-menu"}>
                            <li>
                                <Link href={"/"}>
                                    <Image
                                        className={"nav-logo"}
                                        src="https://cdn.renfei.net/Logo/RF_white.svg"
                                        alt={""}
                                        height={30}
                                        width={30}
                                    />
                                </Link>
                            </li>
                            <li>
                                <Link href={"/"}>
                                    <a>首页</a>
                                </Link>
                            </li>
                            <li>
                                <Link href={"/posts"}>
                                    <a>文章</a>
                                </Link>
                            </li>
                            <li>
                                <Link href={"/weibo"}>
                                    <a>微博</a>
                                </Link>
                            </li>
                            <li>
                                <Link href={"/photo"}>
                                    <a>相册</a>
                                </Link>
                            </li>
                            <li>
                                <a href={"https://bbs.renfei.net"} target={"_blank"}>论坛</a>
                            </li>
                            <li>
                                <Link href={"/kitbox"}>
                                    <a>工具箱</a>
                                </Link>
                            </li>
                            <li>
                                <Link href={"/docs"}>
                                    <a>在线文档</a>
                                </Link>
                            </li>
                            <li>
                                <Link href={"/"}>
                                    <a>更多</a>
                                </Link>
                            </li>
                            <li>
                                <Link href={"javascript:void(0)"}>
                                    <SearchOutlined/>
                                </Link>
                            </li>
                        </ul>
                    </div>
                </nav>
            </Affix>
        </>
    )
}

export default Navbar
