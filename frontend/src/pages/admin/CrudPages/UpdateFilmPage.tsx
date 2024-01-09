import React, {FC} from 'react';
import CrudTableLayout from "../CrudTableLayout/CrudTableLayout";
import FilmEditForm from "../../../components/admin/FilmEditForm/FilmEditForm";

const CreateFilmPage: FC = () => {

    return (
        <CrudTableLayout title="Обновить фильм" crudTable={
            <FilmEditForm/>
        }/>
    );
};

export default CreateFilmPage;