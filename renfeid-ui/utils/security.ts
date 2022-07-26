export const switchSecretLevelText = (secretLevel: string) => {
    switch (secretLevel) {
        case 'UNCLASSIFIED':
            return '非密'
        case 'INTERNAL':
            return '内部'
        case 'SECRET':
            return '秘密'
        case 'CONFIDENTIAL':
            return '机密'
        default:
            return secretLevel
    }
}