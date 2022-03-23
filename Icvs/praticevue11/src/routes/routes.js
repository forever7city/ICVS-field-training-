import home from "../views/home"
import Login from "../views/Login";
import Main from "../views/Main";
import Menu from "../views/Menu";
import Ir from "../views/Ir";
import Locative from "@/views/Locative";

const routes = [
    {
        path: '/',
        redirect: 'main',
        component: home,
        children: [
            {
                path: '/login',
                name: 'login',
                component: Login
            },
            {
                path: '/main',
                name: 'main',
                component: Main
            },
            {
                path: '/menu',
                name: 'menu',
                component: Menu
            },
            {
                path: '/gsicsAmiIr',
                name: 'gsicsAmiIr',
                component: Ir
            },
            {
                path: '/locate',
                name: 'locate',
                component: Locative
            }
        ],
    },
];

export default routes;