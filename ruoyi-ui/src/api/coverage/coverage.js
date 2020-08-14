import request from '@/utils/request'

// 查询险种责任信息列表
export function listCoverage(query) {
  return request({
    url: '/coverage/coverage/list',
    method: 'get',
    params: query
  })
}

// 查询险种责任信息详细
export function getCoverage(id) {
  return request({
    url: '/coverage/coverage/' + id,
    method: 'get'
  })
}

// 新增险种责任信息
export function addCoverage(data) {
  return request({
    url: '/coverage/coverage',
    method: 'post',
    data: data
  })
}

// 修改险种责任信息
export function updateCoverage(data) {
  return request({
    url: '/coverage/coverage',
    method: 'put',
    data: data
  })
}

// 删除险种责任信息
export function delCoverage(id) {
  return request({
    url: '/coverage/coverage/' + id,
    method: 'delete'
  })
}
