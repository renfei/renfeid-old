const NUMBER_REGEX = /^\d+$/i

export const isNumber = (str: string) => NUMBER_REGEX.test(str)
