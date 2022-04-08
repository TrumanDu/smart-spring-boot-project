import {
  commonAdd,
  commonBatchRemove,
  commonQueryList,
  commonTreeSelectList,
  commonUpdate,
} from '@/services/ant-design-pro/api';
import { PlusOutlined } from '@ant-design/icons';
import ProForm, {
  ModalForm,
  ProFormText,
  ProFormTextArea,
  ProFormTreeSelect,
} from '@ant-design/pro-form';
import { FooterToolbar, PageContainer } from '@ant-design/pro-layout';
import type { ActionType } from '@ant-design/pro-table';
import ProTable from '@ant-design/pro-table';
import { Button, message, Popconfirm } from 'antd';
import { useRef, useState } from 'react';
import { FormattedMessage } from 'umi';

type OrgItem = {
  id: number;
  orgName: string;
  orgCode: string;
  description: string;
  parentId: number;
};

const handleAdd = async (fields?: any) => {
  const hide = message.loading('Adding');
  try {
    hide();
    const response = await commonAdd('/api/sys_org/add', {
      orgName: fields?.orgName,
      orgCode: fields?.orgCode,
      description: fields?.description,
      parentId: fields?.parentId?.value,
    });
    if (response.message == 'OK') {
      message.success('Added successfully');
    }
    return true;
  } catch (error) {
    hide();
    return false;
  }
};

const handleUpdate = async (fields: any) => {
  const hide = message.loading('Modifying');
  try {
    //修改
    hide();
    const response = await commonUpdate('/api/sys_org/update', {
      id: fields?.id,
      orgName: fields?.orgName,
      orgCode: fields?.orgCode,
      description: fields?.description,
      parentId: fields?.parentId?.value,
    });
    if (response.message == 'OK') {
      message.success('Modify successfully');
    }
    return true;
  } catch (error) {
    hide();
    message.error('Modify failed, please try again!');
    return false;
  }
};

const handleRemove = async (selectedRows: OrgItem[]) => {
  const hide = message.loading('Deleting');
  if (!selectedRows) return true;
  try {
    await commonBatchRemove('/api/sys_org/delete', {
      ids: selectedRows.map((row) => row.id),
    });
    hide();
    message.success('Deleted successfully and will refresh soon');
    return true;
  } catch (error) {
    hide();
    message.error('Delete failed, please try again');
    return false;
  }
};

function Org() {
  const actionRef = useRef<ActionType>();
  const [createModalVisible, handleModalVisible] = useState<boolean>(false);
  const [updateModalVisible, handleUpdateModalVisible] = useState<boolean>(false);
  const [modifyOrg, setModifyOrg] = useState<OrgItem>();
  const [selectedRowsState, setSelectedRows] = useState<OrgItem[]>([]);

  const columns = [
    {
      title: 'OrgName',
      dataIndex: 'orgName',
      key: 'orgName',
    },
    {
      title: 'OrgCode',
      dataIndex: 'orgCode',
      key: 'orgCode',
      width: '12%',
    },
    {
      title: 'Description',
      dataIndex: 'description',
      key: 'description',
    },
    {
      title: 'Option',
      valueType: 'option',
      key: 'option',
      width: '10%',
      render: (_, record?: any) => [
        <a
          key="editable"
          onClick={() => {
            handleUpdateModalVisible(true);
            setModifyOrg(record);
          }}
        >
          Edit
        </a>,
      ],
    },
  ];

  return (
    <PageContainer
      header={{
        breadcrumb: {},
      }}
    >
      <ProTable
        headerTitle={'Org List'}
        cardBordered
        actionRef={actionRef}
        rowKey={(record) => record.id}
        columns={columns}
        rowSelection={{
          onChange: (_, selectedRows) => {
            setSelectedRows(selectedRows);
          },
        }}
        search={false}
        request={() => commonQueryList('/api/sys_org/list')}
        pagination={{ showSizeChanger: true }}
        toolBarRender={() => [
          <Button
            type="primary"
            key="primary"
            onClick={() => {
              handleModalVisible(true);
            }}
          >
            <PlusOutlined /> <FormattedMessage id="pages.searchTable.new" defaultMessage="New" />
          </Button>,
        ]}
      />

      {selectedRowsState?.length > 0 && (
        <FooterToolbar
          extra={
            <div>
              <FormattedMessage id="pages.searchTable.chosen" defaultMessage="Chosen" />{' '}
              <a style={{ fontWeight: 600 }}>{selectedRowsState.length}</a>{' '}
              <FormattedMessage id="pages.searchTable.item" defaultMessage="项" />
              &nbsp;&nbsp;
            </div>
          }
        >
          <Popconfirm
            key="delete"
            title="Are you sure to delete ?"
            onConfirm={async () => {
              await handleRemove(selectedRowsState);
              setSelectedRows([]);
              actionRef.current?.reloadAndRest?.();
            }}
            okText="Yes"
            cancelText="No"
          >
            <Button type="primary" danger>
              <FormattedMessage
                id="pages.searchTable.batchDeletion"
                defaultMessage="Batch deletion"
              />
            </Button>
          </Popconfirm>
          ,
        </FooterToolbar>
      )}

      <ModalForm
        title="New Org"
        layout="vertical"
        modalProps={{ destroyOnClose: true }}
        visible={createModalVisible}
        onVisibleChange={handleModalVisible}
        onFinish={async (value) => {
          const success = await handleAdd(value);
          if (success) {
            handleModalVisible(false);
            if (actionRef.current) {
              actionRef.current.reload();
            }
          }
        }}
      >
        <ProForm.Group>
          <ProFormText
            rules={[
              {
                required: true,
                message: 'Org Name is required',
              },
            ]}
            width="md"
            name="orgName"
            label="OrgName"
          />
          <ProFormText
            rules={[
              {
                required: true,
                message: 'Org Code is required',
              },
            ]}
            width="md"
            name="orgCode"
            label="OrgCode"
          />
        </ProForm.Group>
        <ProFormTreeSelect
          name="parentId"
          label="Parent Org"
          placeholder="Please select"
          allowClear
          width={330}
          secondary
          request={() => commonTreeSelectList('/api/sys_org/list/tree_select')}
          // tree-select args
          fieldProps={{
            treeLine: true,
            showArrow: false,
            filterTreeNode: true,
            showSearch: true,
            dropdownMatchSelectWidth: false,
            labelInValue: true,
            autoClearSearchValue: true,
            treeNodeFilterProp: 'title',
            fieldNames: {
              label: 'title',
            },
          }}
        />
        <ProFormTextArea name="description" label="Description" />
      </ModalForm>

      <ModalForm
        title="Modify Org"
        layout="vertical"
        modalProps={{ destroyOnClose: true }}
        visible={updateModalVisible}
        onVisibleChange={handleUpdateModalVisible}
        onFinish={async (value) => {
          const success = await handleUpdate(value);
          if (success) {
            handleUpdateModalVisible(false);
            if (actionRef.current) {
              actionRef.current.reload();
            }
          }
        }}
      >
        <ProFormText initialValue={modifyOrg?.id} name="id" hidden />
        <ProForm.Group>
          <ProFormText
            rules={[
              {
                required: true,
                message: 'Org Name is required',
              },
            ]}
            initialValue={modifyOrg?.orgName}
            width="md"
            name="orgName"
            label="OrgName"
          />
          <ProFormText
            rules={[
              {
                required: true,
                message: 'Org Code is required',
              },
            ]}
            initialValue={modifyOrg?.orgCode}
            width="md"
            name="orgCode"
            label="OrgCode"
          />
        </ProForm.Group>

        <ProFormTreeSelect
          name="parentId"
          label="Parent Org"
          placeholder="Please select"
          allowClear
          width={330}
          secondary
          request={() => commonTreeSelectList('/api/sys_org/list/tree_select')}
          // tree-select args
          fieldProps={{
            defaultValue: modifyOrg?.parentId,
            disabled: true,
            treeLine: true,
            showArrow: false,
            filterTreeNode: true,
            showSearch: true,
            dropdownMatchSelectWidth: false,
            labelInValue: true,
            autoClearSearchValue: true,
            treeNodeFilterProp: 'title',
            fieldNames: {
              label: 'title',
            },
          }}
        />

        <ProFormTextArea
          name="description"
          label="Description"
          initialValue={modifyOrg?.description}
        />
      </ModalForm>
    </PageContainer>
  );
}

export default Org;
