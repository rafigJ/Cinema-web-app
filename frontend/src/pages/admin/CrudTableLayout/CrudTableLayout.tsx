import React, {FC} from 'react';
import {Layout} from "antd";
import "./CrudTableLayout.css"

interface CrudTableLayoutProps {
    title: string;
    crudTable: React.ReactNode;
}

const CrudTableLayout:FC<CrudTableLayoutProps> = ({title, crudTable}) => {
    return (
        <Layout>
            <Layout.Header style={{padding: 0, background: "white"}}>
                <span color='white' className='title'>{title}</span>
            </Layout.Header>
            <Layout.Content style={{margin: '24px 16px 0'}}>
                <div
                    style={{
                        padding: 24,
                        minHeight: 360,
                        background: "white",
                        borderRadius: "8px",
                    }}
                >
                    {crudTable}
                </div>
            </Layout.Content>
        </Layout>
    );
};

export default CrudTableLayout;