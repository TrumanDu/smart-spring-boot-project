import {
  commonAdd,
  commonBatchRemove,
  commonQueryList,
  commonTreeSelectList,
  commonUpdate,
} from '@/services/ant-design-pro/api';
import { PlusOutlined } from '@ant-design/icons';
import { ModalForm, ProFormText, ProFormTextArea, ProFormTreeSelect } from '@ant-design/pro-form';
import { FooterToolbar, PageContainer } from '@ant-design/pro-layout';
import type { ActionType } from '@ant-design/pro-table';
import ProTable from '@ant-design/pro-table';
import { Button, message, Popconfirm } from 'antd';
import type { ReactNode } from 'react';
import { useRef, useState } from 'react';
import { FormattedMessage } from 'umi';
import parse from 'html-react-parser';

type MenuItem = {
  id: number;
  menuName: string;
  menuUrl: string;
  menuIcon: string;
  description: string;
  parentId: number;
  sort: number;
};

const handleAdd = async (fields?: any) => {
  const hide = message.loading('Adding');
  try {
    hide();
    const response = await commonAdd('/api/sys_menu/add', {
      parentId: fields?.parentId?.value,
      menuName: fields?.menuName,
      menuIcon: fields?.menuIcon,
      menuUrl: fields?.menuUrl,
      sort: fields?.sort,
      description: fields?.description,
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
    const response = await commonUpdate('/api/sys_menu/update', {
      parentId: fields?.parentId?.value,
      id: fields?.id,
      menuName: fields?.menuName,
      menuIcon: fields?.menuIcon,
      menuUrl: fields?.menuUrl,
      sort: fields?.sort,
      description: fields?.description,
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

const handleRemove = async (selectedRows: MenuItem[]) => {
  const hide = message.loading('Deleting');
  if (!selectedRows) return true;
  try {
    await commonBatchRemove('/api/sys_menu/delete', {
      ids: selectedRows.map((row) => row.id),
    });
    hide();
    message.success('Deleted successfully');
    return true;
  } catch (error) {
    hide();
    message.error('Delete failed, please try again');
    return false;
  }
};

function Menu() {
  const actionRef = useRef<ActionType>();
  const [createModalVisible, handleModalVisible] = useState<boolean>(false);
  const [updateModalVisible, handleUpdateModalVisible] = useState<boolean>(false);
  const [modifyObj, setModifyObj] = useState<MenuItem>();
  const [selectedRowsState, setSelectedRows] = useState<MenuItem[]>([]);
  const [menuIconPreview, setMenuIconPreview] = useState();

  const columns = [
    {
      title: 'MenuName',
      dataIndex: 'menuName',
      key: 'menuName',
    },
    {
      title: 'MenuUrl',
      dataIndex: 'menuUrl',
      key: 'menuUrl',
      width: '12%',
    },
    {
      title: 'MenuIcon',
      dataIndex: 'menuIcon',
      key: 'menuIcon',
      render: (text: ReactNode, record?: any) => (record.menuIcon ? parse(record.menuIcon) : ''),
    },
    {
      title: 'Description',
      dataIndex: 'description',
      key: 'description',
    },
    {
      title: 'Sort',
      dataIndex: 'sort',
      key: 'sort',
    },
    {
      title: 'Option',
      valueType: 'option',
      key: 'option',
      width: '10%',
      render: (text: ReactNode, record?: any) => [
        <a
          key="editable"
          onClick={() => {
            handleUpdateModalVisible(true);
            if (record.menuIcon) {
              const preview = parse(record.menuIcon);
              setMenuIconPreview(preview);
            } else {
              setMenuIconPreview('');
            }
            setModifyObj(record);
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
        headerTitle={'Menu List'}
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
        request={() => commonQueryList('/api/sys_menu/list')}
        pagination={{ showSizeChanger: true }}
        toolBarRender={() => [
          <Button
            type="primary"
            key="primary"
            onClick={() => {
              handleModalVisible(true);
              setMenuIconPreview(null);
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
        title="New Menu"
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
        <ProFormTreeSelect
          name="parentId"
          label="Parent menu"
          placeholder="Please select"
          allowClear
          secondary
          request={() => commonTreeSelectList('/api/sys_menu/list/tree_select')}
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
        <ProFormText
          rules={[
            {
              required: true,
              message: 'Menu Name is required',
            },
          ]}
          name="menuName"
          label="MenuName"
        />
        <ProFormText
          name="menuIcon"
          label="MenuIcon"
          fieldProps={{
            size: 'large',
            prefix: menuIconPreview,
            onChange: (e) => {
              const { value } = e.target;
              const preview = parse(value);
              setMenuIconPreview(preview);
            },
          }}
        />
        <ProFormText
          rules={[
            {
              required: true,
              message: 'Menu Url is required',
            },
          ]}
          name="menuUrl"
          label="MenuUrl"
        />
        <ProFormText
          rules={[
            {
              required: true,
              message: 'Sort is required',
            },
          ]}
          name="sort"
          label="Sort"
          fieldProps={{
            type: 'number',
            min: 0,
          }}
        />
        <ProFormTextArea name="description" label="Description" />
      </ModalForm>

      <ModalForm
        title="Modify Menu"
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
        <ProFormText initialValue={modifyObj?.id} name="id" hidden />

        <ProFormTreeSelect
          name="parentId"
          label="Parent menu"
          placeholder="Please select"
          allowClear
          secondary
          request={() => commonTreeSelectList('/api/sys_menu/list/tree_select')}
          // tree-select args
          fieldProps={{
            defaultValue: modifyObj?.parentId,
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
        <ProFormText
          rules={[
            {
              required: true,
              message: 'Menu Name is required',
            },
          ]}
          name="menuName"
          label="MenuName"
          initialValue={modifyObj?.menuName}
        />
        <ProFormText
          name="menuIcon"
          label="MenuIcon"
          initialValue={modifyObj?.menuIcon}
          fieldProps={{
            size: 'large',
            prefix: menuIconPreview,
            onChange: (e) => {
              const { value } = e.target;
              const preview = parse(value);
              setMenuIconPreview(preview);
            },
          }}
        />
        <ProFormText
          rules={[
            {
              required: true,
              message: 'Menu Url is required',
            },
          ]}
          name="menuUrl"
          label="MenuUrl"
          initialValue={modifyObj?.menuUrl}
        />
        <ProFormText
          rules={[
            {
              required: true,
              message: 'Sort is required',
            },
          ]}
          name="sort"
          label="Sort"
          initialValue={modifyObj?.sort}
          fieldProps={{
            type: 'number',
            min: 0,
          }}
        />
        <ProFormTextArea
          name="description"
          label="Description"
          initialValue={modifyObj?.description}
        />
      </ModalForm>
    </PageContainer>
  );
}

export default Menu;
