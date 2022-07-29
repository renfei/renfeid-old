export const toPascalCase = (str: string) => {
  return str.replace(
    /(\w)(\w*)/g,
    (g0, g1, g2) => `${g1.toUpperCase()}${g2.toLowerCase()}`
  )
}
