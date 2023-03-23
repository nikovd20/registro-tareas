<template>
   <div id="app">
    <myToast />
        <section class="top" style="display: flex;align-items: center;flex-direction: column;">
            <h1>Registro de Tareas</h1>
            <h3>Aplicacion Web para registrar tareas</h3>
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
        <!-- Venta de creacion tarea -->
        <section class="dialog">
            <DataDialog v-model:visible="visible" header="Crear Tarea" :style="{ width: '800px' }" >
                <!-- Nombre de Tarea -->
                <span class="p-float-label">
                    <InputText id="nombre" v-model="task.title" type="text" :class="{ 'p-invalid': errorMessage }" aria-describedby="text-error" />
                    <label for="nombre">Nombre</label >
                </span>
                <!-- Descripcion -->
                <span class="p-float-label" :style="{ width: 'auto' }">
                    <InputText id="descripcion" v-model="task.description"  />
                    <label for="descripcion">Descripcion</label >
                </span>
                <!-- Estado de tarea -->
                <div class="card flex justify-content-center">
                    <DropDown v-model="task.state" :options="estados" optionLabel="name" placeholder="Selecciona un estado" class="w-full md:w-14rem" optionValue="value" />
                </div>
                <!-- Botones -->
                <template #footer>
                    <myButton label="Cancelar" icon="pi pi-times" @click="unVisible()"  />
                    <myButton label="Guardar" type="submit" icon="pi pi-check" @click="save()"  />
                </template> 
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
                id: null,
                title: null,
                description: null,
                creationDate: null,
                state: null
            },
            selectedTask :{
                id: null,
                title: null,
                description: null,
                creationDate: null,
                state: null
            },
            estados: [
                { name: 'En Proceso', value: 'En Proceso' },
                { name: 'Pendiente', value: 'Pendiente' },
                { name: 'Terminado', value: 'Terminado' }
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
                // {
                //     label: 'Listar',
                    
                //     icon: 'pi pi-fw pi-bars',
                //     items : [
                //         {
                //             label: 'ID',
                //             icon: 'pi pi-fw pi-align-justify',
                //         },
                //         {
                //             label: 'Fechas',
                //             icon: 'pi pi-fw pi-calendar',
                //         },
                //     ]
                // },
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
            this.registerService.save(this.task).then(data => {
                if (data.status === 200) {
                    this.task ={
                        id: null,
                        title: null,
                        description: null,
                        creationDate: null,
                        state : null
                    };
                    this.$toast.add({ severity: 'success', summary: 'Confirmado!!', detail: 'Se realizo la accion correctamente', life: 3000 });
                    this.visible = false;
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
.top {
    background-color: black;
    font-family: Verdana;
}
h3{
    margin-top: 5px;
}
</style>