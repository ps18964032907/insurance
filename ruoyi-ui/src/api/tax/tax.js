import request from '@/utils/request'

// 查询车船税信息列表
export function listTax(query) {
  return request({
    url: '/tax/tax/list',
    method: 'get',
    params: query
  })
}

// 查询车船税信息详细
export function getTax(id) {
  return request({
    url: '/tax/tax/' + id,
    method: 'get'
  })
}

// 新增车船税信息
export function addTax(data) {
  return request({
    url: '/tax/tax',
    method: 'post',
    data: data
  })
}

// 修改车船税信息
export function updateTax(data) {
  return request({
    url: '/tax/tax',
    method: 'put',
    data: data
  })
}

// 删除车船税信息
export function delTax(id) {
  return request({
    url: '/tax/tax/' + id,
    method: 'delete'
  })
}
