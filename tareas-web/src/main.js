import { createApp } from 'vue'
import App from './App.vue'
import PrimeVue from 'primevue/config';
import InputText from 'primevue/inputtext';
import Button from 'primevue/button';
import Toast from 'primevue/toast';
import ToastService from 'primevue/toastservice';
import DataTable from 'primevue/datatable';
import Column from 'primevue/column';
import Menubar from 'primevue/menubar';
import Dialog from 'primevue/dialog';
import Dropdown from 'primevue/dropdown';
import { useField, useForm } from 'vee-validate';


//theme
import "primevue/resources/themes/md-dark-indigo/theme.css";     
//core
import "primevue/resources/primevue.min.css";
//icons
import "primeicons/primeicons.css";


const app = createApp(App);
app.use(PrimeVue);
app.use(ToastService);
app.use(useField);
app.use(useForm);

app.component('DataTable', DataTable);
app.component('DataColumn', Column);
app.component('myButton', Button);
app.component('InputText', InputText);
app.component('myToast', Toast);
app.component('menuBar', Menubar)
app.component('DataDialog', Dialog)
app.component('DropDown', Dropdown)
app.mount('#app');

