export const getSysTypeUrl = (sysType: string) => {
  let url = ''
  switch (sysType) {
    case 'POSTS':
      url += '/posts/'
      break
    case 'ALBUM':
      url += '/photo/'
      break
    case 'WEIBO':
      url += '/weibo/'
      break
    case 'KITBOX':
      url += '/kitbox/'
      break
    case 'PAGE':
      url += '/page/'
      break
    default:
      url += sysType
      break
  }
  return url
}