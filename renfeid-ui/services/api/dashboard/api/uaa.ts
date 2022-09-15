import * as Fetch from '../../../../utils/request'
import APIResult = API.APIResult
import UserDetail = API.UserDetail
import RoleDetail = API.RoleDetail
import ResetPasswordAo = API.ResetPasswordAo
import MenuTree = API.MenuTree

// 查询用户
export const queryUserList = async (username?: string, email?: string, phone?: string, ip?: string,
    secretLevel?: string, locked?: boolean, enabled?: boolean, page?: number, rows?: number) => {
    let url = `/api/dash/uaa/user?`
    if (username) {
        url += 'username=' + username + '&'
    }
    if (email) {
        url += 'email=' + email + '&'
    }
    if (phone) {
        url += 'phone=' + phone + '&'
    }
    if (ip) {
        url += 'ip=' + ip + '&'
    }
    if (secretLevel) {
        url += 'secretLevel=' + secretLevel + '&'
    }
    if (locked) {
        url += 'locked=' + locked + '&'
    }
    if (enabled) {
        url += 'enabled=' + enabled + '&'
    }
    if (page) {
        url += 'pages=' + page + '&'
    }
    if (rows) {
        url += 'rows=' + rows + '&'
    }
    return await fetch(url, {
        method: 'GET',
    }).then((res: any) => {
        return res.json()
    }).catch((error: any) => {
        return Promise.reject(error)
    })
}

// 创建用户
export const createUser = async (userDetail: UserDetail) => {
    let url = `/api/dash/uaa/user`
    return await fetch(url, {
        method: 'POST',
        body: JSON.stringify(userDetail),
        headers: {
            'content-type': 'application/json',
            'Authorization': '',
        }
    }).then((res: any) => {
        return res.json()
    }).catch((error: any) => {
        return Promise.reject(error)
    })
}

// 修改用户资料
export const updateUser = async (userDetail: UserDetail) => {
    let url = `/api/dash/uaa/user/${userDetail.id}`
    return await fetch(url, {
        method: 'PUT',
        body: JSON.stringify(userDetail),
        headers: {
            'content-type': 'application/json',
            'Authorization': '',
        }
    }).then((res: any) => {
        return res.json()
    }).catch((error: any) => {
        return Promise.reject(error)
    })
}

// 给用户定密
export const determineUserSecretLevel = async (id: string, secretLevel: string) => {
    let url = `/api/dash/uaa/user/${id}/secret-level/${secretLevel}`
    return await fetch(url, {
        method: 'PUT',
    }).then((res: any) => {
        return res.json()
    }).catch((error: any) => {
        return Promise.reject(error)
    })
}

// 用户启用或禁用
export const enableUser = async (id: string, enable: boolean) => {
    let url = `/api/dash/uaa/user/${id}/enable/${enable}`
    return await fetch(url, {
        method: 'PUT',
    }).then((res: any) => {
        return res.json()
    }).catch((error: any) => {
        return Promise.reject(error)
    })
}

// 重置用户密码
export const resetPassword = async (id: string, resetPasswordAo: ResetPasswordAo) => {
    let url = `/api/dash/uaa/user/${id}/reset-password`
    return await fetch(url, {
        method: 'PUT',
        body: JSON.stringify(resetPasswordAo),
        headers: {
            'content-type': 'application/json',
            'Authorization': '',
        }
    }).then((res: any) => {
        return res.json()
    }).catch((error: any) => {
        return Promise.reject(error)
    })
}

// 编辑用户角色
export const authorizationRoleByUser = async (id: string, RoleDetails: RoleDetail[]) => {
    let url = `/api/dash/uaa/user/${id}/role`
    return await fetch(url, {
        method: 'PUT',
        body: JSON.stringify(RoleDetails),
        headers: {
            'content-type': 'application/json',
            'Authorization': '',
        }
    }).then((res: any) => {
        return res.json()
    }).catch((error: any) => {
        return Promise.reject(error)
    })
}

// 查询角色
export const queryRoleList = async (roleName?: string, page?: number, rows?: number) => {
    let url = `/api/dash/uaa/role?`
    if (roleName) {
        url += 'roleName=' + roleName + '&'
    }
    if (page) {
        url += 'pages=' + page + '&'
    }
    if (rows) {
        url += 'rows=' + rows + '&'
    }
    return await fetch(url, {
        method: 'GET',
    }).then((res: any) => {
        return res.json()
    }).catch((error: any) => {
        return Promise.reject(error)
    })
}

// 查询角色
export const queryRoleListInner = async (token: string, headers: Headers, roleName?: string, page?: number, rows?: number) => {
    let url = `/_/api/uaa/role?`
    if (roleName) {
        url += 'roleName=' + roleName + '&'
    }
    if (page) {
        url += 'pages=' + page + '&'
    }
    if (rows) {
        url += 'rows=' + rows + '&'
    }
    return Fetch.get(url, headers, token, true)
}

// 创建角色
export const createRole = async (roleDetail: RoleDetail) => {
    let url = `/api/dash/uaa/role`
    return await fetch(url, {
        method: 'POST',
        body: JSON.stringify(roleDetail),
        headers: {
            'content-type': 'application/json',
            'Authorization': '',
        }
    }).then((res: any) => {
        return res.json()
    }).catch((error: any) => {
        return Promise.reject(error)
    })
}

// 修改角色
export const updateRole = async (roleDetail: RoleDetail) => {
    let url = `/api/dash/uaa/role/${roleDetail.id}`
    return await fetch(url, {
        method: 'PUT',
        body: JSON.stringify(roleDetail),
        headers: {
            'content-type': 'application/json',
            'Authorization': '',
        }
    }).then((res: any) => {
        return res.json()
    }).catch((error: any) => {
        return Promise.reject(error)
    })
}

// 删除角色
export const deleteRole = async (id: string) => {
    let url = `/api/dash/uaa/role/${id}`
    return await fetch(url, {
        method: 'DELETE',
    }).then((res: any) => {
        return res.json()
    }).catch((error: any) => {
        return Promise.reject(error)
    })
}

// 查询系统API接口列表
export const queryAllMenuTreeInner = async (token: string, headers: Headers) => {
    let url = `/_/api/uaa/menu/tree/all`
    return Fetch.get(url, headers, token, true)
}

// 查询系统API接口列表
export const queryAllMenuTree = async () => {
    let url = `/api/dash/uaa/menu/tree/all`
    return await fetch(url, {
        method: 'GET',
    }).then((res: any) => {
        return res.json()
    }).catch((error: any) => {
        return Promise.reject(error)
    })
}

// 创建菜单
export const createMenu = async (menu: MenuTree) => {
    let url = `/api/dash/uaa/menu`
    return await fetch(url, {
        method: 'POST',
        body: JSON.stringify(menu),
        headers: {
            'content-type': 'application/json',
            'Authorization': '',
        }
    }).then((res: any) => {
        return res.json()
    }).catch((error: any) => {
        return Promise.reject(error)
    })
}

// 修改菜单
export const updateMenu = async (menu: MenuTree) => {
    let url = `/api/dash/uaa/menu/${menu.id}`
    return await fetch(url, {
        method: 'PUT',
        body: JSON.stringify(menu),
        headers: {
            'content-type': 'application/json',
            'Authorization': '',
        }
    }).then((res: any) => {
        return res.json()
    }).catch((error: any) => {
        return Promise.reject(error)
    })
}

// 删除菜单
export const deleteMenu = async (id: string) => {
    let url = `/api/dash/uaa/menu/${id}`
    return await fetch(url, {
        method: 'DELETE'
    }).then((res: any) => {
        return res.json()
    }).catch((error: any) => {
        return Promise.reject(error)
    })
}

// 查询系统API接口列表
export const queryMenuTreeInner = async (token: string, headers: Headers) => {
    let url = `/_/api/uaa/menu/tree`
    return Fetch.get(url, headers, token, true)
}

// 查询系统API接口列表
export const queryMenuTree = async () => {
    let url = `/api/dash/uaa/menu/tree`
    return await fetch(url, {
        method: 'GET',
    }).then((res: any) => {
        return res.json()
    }).catch((error: any) => {
        return Promise.reject(error)
    })
}