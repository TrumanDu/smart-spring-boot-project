import { commonAdd, commonQueryList } from '@/services/ant-design-pro/api';
import { Button, Col, message, Row, Tree } from 'antd';
import { useEffect, useImperativeHandle, useState } from 'react';

function RoleMenu(props: any) {
  const [dataSource, setDataSource] = useState([]);
  const [checkedKeys, setCheckedKeys] = useState([]);
  const { roleId, menuChildRef } = props;

  const fetchData = async () => {
    const hide = message.loading('Loading');
    hide();
    try {
      const result = await commonQueryList(`/api/sys_role_menu/list/${roleId}`);
      hide();
      if (result.success) {
        setCheckedKeys(result.data.checkedKeys);
        setDataSource(result.data.menuVOList);
      }
    } catch (error) {
      hide();
      message.error('Query failed, please try again!');
    }
  };

  const onCheck = (checkedKeysValue: any) => {
    setCheckedKeys(checkedKeysValue.checked);
  };

  const handleSave = async () => {
    const hide = message.loading('Saving');
    try {
      hide();
      const response = await commonAdd('/api/sys_role_menu/add', {
        roleId: roleId,
        menuIds: checkedKeys,
      });
      hide();
      if (response.message == 'OK') {
        message.success('Saved successfully');
      }
    } catch (error) {
      hide();
    }
  };

  useImperativeHandle(menuChildRef, () => ({
    fetchData,
  }));

  useEffect(() => {
    if (roleId) {
      fetchData();
    }
  }, [roleId]);
  return (
    <Row>
      <Col span={16}>
        {dataSource?.length > 0 && (
          <Tree
            checkable
            checkStrictly
            defaultExpandAll
            onCheck={onCheck}
            checkedKeys={checkedKeys}
            fieldNames={{ title: 'menuName', key: 'id' }}
            treeData={dataSource}
          />
        )}
      </Col>
      <Col>
        <Button type="primary" disabled={roleId == null ? true : false} onClick={handleSave}>
          Save
        </Button>
      </Col>
    </Row>
  );
}

export default RoleMenu;
