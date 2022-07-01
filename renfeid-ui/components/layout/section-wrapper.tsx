const SectionWrapper = ({children}: any) => {
    return (
        <>
            {
                children && (
                    <div className={"section-wrapper"}>
                        <div className={"renfeid-content"}>
                            {children}
                        </div>
                    </div>
                )
            }
        </>
    )
}

export default SectionWrapper
