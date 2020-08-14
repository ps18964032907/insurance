import request from '@/utils/request'

// 查询保单主要信息列表
export function listPolicy() {
  return request({
    url: '/combined/policy/list',
    method: 'get'
  })
}

// 查询保单主要信息详细
export function getPolicy(id) {
  return request({
    url: '/combined/combined/policy/' + id,
    method: 'get'
  })
}

// 新增保单主要信息
export function addPolicy(data) {
  return request({
    url: '/combined/combined/policy',
    method: 'post',
    data: data
  })
}

// 提交核保
export function updatePolicy(data) {
  return request({
    url: '/combined/policy',
    method: 'put',
    data: data
  })
}

// 删除保单主要信息
export function delPolicy(id) {
  return request({
    url: '/combined/policy/' + id,
    method: 'delete'
  })
}
