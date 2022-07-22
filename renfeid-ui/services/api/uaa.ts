import SecretKey = API.SecretKey;
import SignInAo = API.SignInAo;

export const requestServerSecretKey = async () => {
    let url = `/api/auth/secretKey`
    return await fetch(url, {
        method: 'GET',
        headers: {
            'content-type': 'application/json',
            'Authorization': '',
        }
    }).then((res: any) => {
        return res.json();
    }).catch((error: any) => {
        return Promise.reject(error);
    })
}

export const settingClientSecretKey = async (clientSecretKey: SecretKey) => {
    let url = `/api/auth/secretKey`
    return await fetch(url, {
        method: 'POST',
        body: JSON.stringify(clientSecretKey),
        headers: {
            'content-type': 'application/json',
            'Authorization': '',
        }
    }).then((res: any) => {
        return res.json();
    }).catch((error: any) => {
        return Promise.reject(error);
    })
}

export const signIn = async (signInAo: SignInAo) => {
    let url = `/api/auth/signIn`
    return await fetch(url, {
        method: 'POST',
        body: JSON.stringify(signInAo),
        headers: {
            'content-type': 'application/json',
            'Authorization': '',
        }
    }).then((res: any) => {
        return res.json();
    }).catch((error: any) => {
        return Promise.reject(error);
    })
}

export const signOut = async () => {
    let url = `/api/auth/signOut`
    return await fetch(url, {
        method: 'DELETE',
        headers: {
            'content-type': 'application/json',
            'Authorization': '',
        }
    }).then((res: any) => {
        return res.json();
    }).catch((error: any) => {
        return Promise.reject(error);
    })
}

export const requestCurrentUserInfo = async () => {
    let url = `/api/auth/current/user`
    return await fetch(url, {
        method: 'GET',
        headers: {
            'content-type': 'application/json',
            'Authorization': '',
        }
    }).then((res: any) => {
        return res.json();
    }).catch((error: any) => {
        return Promise.reject(error);
    })
}
