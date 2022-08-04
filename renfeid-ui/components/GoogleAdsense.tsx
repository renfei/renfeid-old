import Script from 'next/script'

const GoogleAdsense = (props: any) => {
    if (process.env.NEXT_PUBLIC_RENFEID_ACTIVE == 'preview') {
        return (<></>)
    } else {
        return (
            <>
                <ins className="adsbygoogle"
                    style={{ display: 'block' }}
                    data-ad-client={props.client}
                    data-ad-slot={props.slot}
                    data-ad-format="auto"
                    data-full-width-responsive="true"></ins>
                <Script
                    id={props.slot}
                >
                    {`(adsbygoogle = window.adsbygoogle || []).push({})`}
                </Script>
            </>
        )
    }
}

export default GoogleAdsense