// @ts-ignore
/* eslint-disable */
import { request } from 'umi';

/** 获取当前的用户 GET /api/currentUser */
export async function currentUser(options?: { [key: string]: any }) {
  return request<{
    data: API.CurrentUser;
  }>('/api/currentUser', {
    method: 'GET',
    ...(options || {}),
  });
}

/** 退出登录接口 POST /api/login/outLogin */
export async function outLogin(options?: { [key: string]: any }) {
  return request<Record<string, any>>('/api/logout', {
    method: 'GET',
    ...(options || {}),
  });
}

/** 登录接口 POST /api/login/account */
export async function login(body: API.LoginParams, options?: { [key: string]: any }) {
  return request('/api/login', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** 此处后端没有提供注释 GET /api/notices */
export async function getNotices(options?: { [key: string]: any }) {
  return request<API.NoticeIconList>('/api/notices', {
    method: 'GET',
    ...(options || {}),
  });
}
/**
 * 查询用户
 * @param params
 * @param sort
 * @param filter
 * @param options
 * @returns
 */
export async function userList(
  params: {
    // query
    /** 当前的页码 */
    current?: number;
    /** 页面的容量 */
    pageSize?: number;
  },
  sort?: any,
  filter?: any,
  options?: { [key: string]: any },
) {
  const response = await request('/api/user/query', {
    method: 'POST',
    data: {
      pageNum: params.current,
      ...params,
    },
  });
  return {
    total: response?.data?.total,
    success: true,
    data: response?.data?.list,
  };
}
export async function addUser(options?: { [key: string]: any }) {
  return request('/api/user/add', {
    method: 'POST',
    data: { ...options },
  });
}
export async function updateUser(options?: { [key: string]: any }) {
  return request('/api/user/update', {
    method: 'PUT',
    data: { ...options },
  });
}
export async function removeUser(id: number) {
  return request<Record<string, any>>(`/api/user/delete/${id}`, {
    method: 'DELETE',
  });
}

export async function sysLogList(
  params: {
    // query
    /** 当前的页码 */
    current?: number;
    /** 页面的容量 */
    pageSize?: number;
  },
  sort?: any,
  filter?: any,
  options?: { [key: string]: any },
) {
  const response = await request('/api/sys_log/query', {
    method: 'POST',
    data: {
      pageNum: params.current,
      ...params,
    },
  });
  return {
    total: response?.data?.total,
    success: true,
    data: response?.data?.list,
  };
}

export async function orgList() {
  const response = await request('/api/sys_org/list', {
    method: 'GET',
  });
  return {
    success: true,
    data: response?.data,
  };
}

export async function orgTreeSelectList() {
  const response = await request('/api/sys_org/list/tree_select', {
    method: 'GET',
  });
  return response?.data;
}

export async function addSysOrg(options?: { [key: string]: any }) {
  return request('/api/sys_org/add', {
    method: 'POST',
    data: {
      orgName: options?.orgName,
      orgCode: options?.orgCode,
      description: options?.description,
      parentId: options?.parentId?.value,
    },
  });
}
export async function updateSysOrg(options?: { [key: string]: any }) {
  return request('/api/sys_org/update', {
    method: 'PUT',
    data: {
      id: options?.id,
      orgName: options?.orgName,
      orgCode: options?.orgCode,
      description: options?.description,
      parentId: options?.parentId?.value,
    },
  });
}
export async function removeSysOrg(id: number) {
  return request<Record<string, any>>(`/api/sys_org/delete/${id}`, {
    method: 'DELETE',
  });
}
