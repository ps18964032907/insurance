import request from '@/utils/request'

// 查询保费计算附加信息列表
export function listCalcsub(query) {
  return request({
    url: '/calcsub/calcsub/list',
    method: 'get',
    params: query
  })
}

// 查询保费计算附加信息详细
export function getCalcsub(id) {
  return request({
    url: '/calcsub/calcsub/' + id,
    method: 'get'
  })
}

// 新增保费计算附加信息
export function addCalcsub(data) {
  return request({
    url: '/calcsub/calcsub',
    method: 'post',
    data: data
  })
}

// 修改保费计算附加信息
export function updateCalcsub(data) {
  return request({
    url: '/calcsub/calcsub',
    method: 'put',
    data: data
  })
}

// 删除保费计算附加信息
export function delCalcsub(id) {
  return request({
    url: '/calcsub/calcsub/' + id,
    method: 'delete'
  })
}
