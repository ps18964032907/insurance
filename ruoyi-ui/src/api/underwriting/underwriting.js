import request from '@/utils/request'

// 显示待收费保单
export function listUnderwriting() {
  return request({
    url: '/combined/combined/collect/list',
    method: 'get'
  })
}


//收费
export function doUnderwriting(data) {
  return request({
    url: '/combined/combined/underwriting?id='+data,
    method: 'post'
  })
}
