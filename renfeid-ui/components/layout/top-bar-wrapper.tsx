const TopBarWrapper = ({children}: any) => {
    return (
        <>
            {
                children && (
                    <div className={"top-bar-wrapper"}>
                        <div className={"renfeid-content"}>
                            {children}
                        </div>
                    </div>
                )
            }
        </>
    )
}

export default TopBarWrapper