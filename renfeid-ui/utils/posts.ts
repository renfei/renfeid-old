import AntdSelectOption = API.AntdSelectOption;

export const switchPostStatus = (postStatus: string) => {
    switch (postStatus) {
        case 'PUBLISH':
            return '发布'
        case 'REVISION':
            return '修订'
        case 'DELETED':
            return '删除'
        case 'OFFLINE':
            return '下线'
        case 'REVIEW':
            return '审核'
        default:
            return postStatus
    }
}

export const getPostStatusList = (): AntdSelectOption[] => {
    let postCatOptions: AntdSelectOption[] = []
    postCatOptions.push({
        label: '发布',
        value: 'PUBLISH'
    }, {
        label: '修订',
        value: 'REVISION'
    }, {
        label: '删除',
        value: 'DELETED'
    }, {
        label: '下线',
        value: 'OFFLINE'
    }, {
        label: '审核',
        value: 'REVIEW'
    })
    return postCatOptions
}