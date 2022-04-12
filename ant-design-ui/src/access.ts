/**
 * @see https://umijs.org/zh-CN/plugins/plugin-access
 * */
export default function access(initialState: { currentUser?: API.CurrentUser } | undefined) {
  const { hasRoutes } = initialState?.currentUser;
  return {
    normalRouteFilter: (route: any) => (hasRoutes ? hasRoutes.includes(route.name) : false), // initialState 中包含了的路由才有权限访问
  };
}
