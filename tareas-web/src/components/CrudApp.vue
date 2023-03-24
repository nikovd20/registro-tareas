<template>
   <div id="app">
    <myToast />
        <section class="top" style="display: flex;align-items: center;flex-direction: column;">
            <img src="../assets/Bannertest1.png" alt="Registro de Tareas" style="padding-top: 10px">
        </section>
        <hr>
        <section class="list" style="margin: 0 auto; width: 80%">
            <menuBar :model="items" />
            <DataTable v-model:selection="selectedTask" :value="tasks" paginator :rows="10" :rowsPerPageOptions="[5, 10, 20, 50]" tableStyle="min-width: 50rem" selectionMode="single" :metaKeySelection="metaKey" dataKey="id">
                <DataColumn field="id" header="ID" style="width: 5%" sortable ></DataColumn>
                <DataColumn field="title" header="Nombre" style="width: 20%"></DataColumn>
                <DataColumn field="description" header="Descripcion" style="width: 35%"></DataColumn>
                <DataColumn field="creationDate" header="Fecha Creacion" style="width: 20% ;" sortable></DataColumn>
                <DataColumn field="state" header="Estado" style="width: 20%" sortable></DataColumn>
            </DataTable>
        </section>
        <!-- Ventana de creacion tarea -->
        <section class="dialog" >
            <DataDialog v-model:visible="visible" header="Crear Tarea" style="width: auto;"> 
                <!-- <form>   -->
                    <section class="c-dialog ">
                        <!-- Nombre de Tarea -->
                        <span class="p-float-label" style="padding-right: 100px;" >
                            <InputText id="nombre" v-model="task.title" type="text" class=" {'p-invalid': !task.nombreValido}"  />
                            <label for="nombre">Nombre</label >
                        </span>
                        <span class="p-message p-error" v-if="!task.nombreValido"> Nombre de tarea es requerido</span>
                        <!-- Estado de tarea -->
                        <div class="card flex justify-content-center ">
                            <DropDown v-model="task.state" :options="estados" optionLabel="name" placeholder="Selecciona un estado" class=" {'p-invalid': !task.stateValido}" optionValue="value" />
                        </div>
                        <span class="p-message p-error" v-if="!task.stateValido"> Estado de tarea es requerido</span>
                        <!-- Descripcion -->
                        <span class="p-float-label " >
                            <TextArea v-model="task.description" rows="5" cols="50" class=" {'p-invalid': !task.descriptionValido}"/>
                            <label>Descripcion</label>
                        </span>
                        <span class="p-message p-error" v-if="!task.descriptionValido"> Descripcion de tarea es requerida</span>
                    </section>
                    <!-- Botones -->
                    <template #footer>
                        <myButton label="Cancelar" icon="pi pi-times" @click="unVisible()"  />
                        <myButton label="Guardar" type="submit" icon="pi pi-check" @click="save()"  />
                    </template>
                <!-- </form> -->
            </DataDialog>
            
        </section>      
   </div>
</template>

<script>
import RegisterService from '../service/RegisterService';

export default{
    name: 'CrudApp', 
    data() {
        return { 
            tasks : null,
            task : {
                title: null,
                description: null,
                state: null,
                nombreValido: true,
                descriptionValido: true,
                stateValido: true
            },
            selectedTask :{
                id: null,
                title: null,
                description: null,
                creationDate: null,
                state: null
            },
            estados: [
                { value: 'En proceso', name: 'En Proceso' },
                { value: 'Pendiente', name: 'Pendiente' },
                { value: 'Terminado', name: 'Terminado' }
            ],
            items : [
                {
                    label: 'Nuevo',
                    icon: 'pi pi-fw pi-plus',
                    command: () => {
                        this.Visible()
                    }
                },
                {
                    label: 'Editar',
                    icon: 'pi pi-fw pi-pencil',
                    command: () => {
                        this.showEdit();
                    }
                },
                {
                    label: 'Borrar',
                    icon: 'pi pi-fw pi-trash',
                    command: () => {
                        this.delete()
                    }
                },
                {
                    label: 'Actualizar',
                    icon: 'pi pi-fw pi-refresh',
                    command: () => {
                        this.getTasks()
                    }
                },            
            ],
            visible: false,
        }      
    },
    registerService : null,
    created(){
        this.registerService = new RegisterService();
    },
    mounted(){
        this.getTasks();
    },
    methods : {
        validarCampos() {
            this.task.nombreValido = !!this.task.title;
            this.task.descriptionValido = !! this.task.description
            this.task.stateValido = !! this.task.state
        },
        Visible(){
            this.visible = true;
        },
        showEdit() {
            this.task = this.selectedTask
            this.visible = true;
        },
        getTasks() {
            this.registerService.getTasks().then(data =>{
                this.tasks = data.data;
            });
        },
        delete(){
            if (confirm("Estas seguro que deseas eliminar esta tarea ?")){
                this.registerService.delete(this.selectedTask.id).then(data => {
                    if(data.status === 200){
                        this.$toast.add({ severity: 'success', summary: 'Atencion!', detail: 'Se elimino la tarea correctamente', life: 3000 });
                        this.getTasks();
                    }
                });
   
            }
        },
        save() {
            this.validarCampos();

            if (!this.task.nombreValido && !this.task.stateValido && !this.task.descriptionValido) {
                this.$toast.add({severity: 'error', summary: 'Error', detail: 'Los campos Nombre, Estado y Descripcion no pueden estar vacios.'});
                return;
            }else if(!this.task.nombreValido && !this.task.stateValido){
                this.$toast.add({severity: 'error', summary: 'Error', detail: 'Los campos Nombre y Estado no pueden estar vacios.'});
                return;
            }else if(!this.task.descriptionValido && !this.task.stateValido){
                this.$toast.add({severity: 'error', summary: 'Error', detail: 'Los campos Estado y Descripcion no pueden estar vacios.'});
                return;
            }else if(!this.task.nombreValido && !this.task.descriptionValido){
                this.$toast.add({severity: 'error', summary: 'Error', detail: 'Los campos Nombre y Descripcion no pueden estar vacios.'});
                return;
            }else if(!this.task.nombreValido){
                this.$toast.add({severity: 'error', summary: 'Error', detail: 'El campo Nombre no pueden estar vacios.'});
                return;
            }else if(!this.task.stateValido){
                this.$toast.add({severity: 'error', summary: 'Error', detail: 'El campo Estado no pueden estar vacios.'});
                return;
            }else if(!this.task.descriptionValido){
                this.$toast.add({severity: 'error', summary: 'Error', detail: 'El campo Descripcion no pueden estar vacios.'});
                return;
            }

            this.registerService.save(this.task).then(data => {          
                    if (data.status === 200) {
                        this.task ={
                            title: null,
                            description: null,
                            state : null
                        };
                        this.visible = false;
                        this.$toast.add({ severity: 'success', summary: 'Confirmado!!', detail: 'Se realizo la accion correctamente', life: 3000 });
                        this.getTasks();
                    }
            });
        },
        unVisible() {
            this.visible = false;
        }

    }
}
</script>

<style>
#app{
    font-family: Verdana;
}
.top {
    background-color: black;
}
h3{
    margin-top: 5px;
}
.c-dialog{
    display: grid;
  grid-template-columns: repeat(1, 1fr);
  gap: 20px;
  margin-top: 10px;
}

</style>