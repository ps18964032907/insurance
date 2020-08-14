import request from '@/utils/request'

// 查询被保险车辆信息列表
export function listInsured(query) {
  return request({
    url: '/insured/insured/list',
    method: 'get',
    params: query
  })
}

// 查询被保险车辆信息详细
export function getInsured(id) {
  return request({
    url: '/insured/insured/' + id,
    method: 'get'
  })
}

// 新增被保险车辆信息
export function addInsured(data) {
  return request({
    url: '/insured/insured',
    method: 'post',
    data: data
  })
}

// 修改被保险车辆信息
export function updateInsured(data) {
  return request({
    url: '/insured/insured',
    method: 'put',
    data: data
  })
}

// 删除被保险车辆信息
export function delInsured(id) {
  return request({
    url: '/insured/insured/' + id,
    method: 'delete'
  })
}
