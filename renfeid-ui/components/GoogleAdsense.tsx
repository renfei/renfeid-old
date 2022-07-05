import React, {useEffect} from "react";

const GoogleAdsense = (props: any) => {
    useEffect(() => {
        try {
            // @ts-ignore
            (window.adsbygoogle = window.adsbygoogle || []).push({});
        } catch (err) {
            console.log(err);
        }
    }, [])

    return (
        <>
            <ins className="adsbygoogle"
                 style={{display: 'block'}}
                 data-ad-client={props.client}
                 data-ad-slot={props.slot}
                 data-ad-format="auto"
                 data-full-width-responsive="true"></ins>
        </>
    )
}

export default GoogleAdsense