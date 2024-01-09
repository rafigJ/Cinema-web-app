import React, {FC} from 'react';
import CrudTableLayout from "../CrudTableLayout/CrudTableLayout";
import FilmCreateForm from "../../../components/admin/FilmCreateForm/FilmCreateForm";

const CreateFilmPage: FC = () => {

    return (
        <CrudTableLayout title="Создать фильм" crudTable={
            <FilmCreateForm/>
        }/>
    );
};

export default CreateFilmPage;