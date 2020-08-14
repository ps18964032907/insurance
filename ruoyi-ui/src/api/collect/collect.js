import request from '@/utils/request'

// 显示待收费保单
export function listCollect() {
  return request({
    url: '/combined/combined/collect/list',
    method: 'get'
  })
}


//收费
export function doCollect(data) {
  return request({
    url: '/combined/combined/collect',
    method: 'post',
    data: data
  })
}
