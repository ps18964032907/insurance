import request from '@/utils/request'

// 查询投保单客户信息（投保人，被保人，车主）列表
export function listCustomer(query) {
  return request({
    url: '/customer/customer/list',
    method: 'get',
    params: query
  })
}

// 查询投保单客户信息（投保人，被保人，车主）详细
export function getCustomer(id) {
  return request({
    url: '/customer/customer/' + id,
    method: 'get'
  })
}

// 新增投保单客户信息（投保人，被保人，车主）
export function addCustomer(data) {
  return request({
    url: '/customer/customer',
    method: 'post',
    data: data
  })
}

// 修改投保单客户信息（投保人，被保人，车主）
export function updateCustomer(data) {
  return request({
    url: '/customer/customer',
    method: 'put',
    data: data
  })
}

// 删除投保单客户信息（投保人，被保人，车主）
export function delCustomer(id) {
  return request({
    url: '/customer/customer/' + id,
    method: 'delete'
  })
}
