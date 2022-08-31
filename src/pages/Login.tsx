import '../styles/new-shop.scss'
import { AiFillPauseCircle, AiOutlineArrowLeft, AiOutlineClose, AiOutlinePlus } from 'react-icons/ai'
import { ChangeEvent, useLayoutEffect, useState } from 'react'
import { MapContainer, TileLayer } from 'react-leaflet'
import { Markers } from '../components/Markers'
import { Header } from '../components/Header'
import { Container } from '../components/Container'
import { Input } from '../components/Input'
import { Button } from '../components/Button'
import { constants } from 'fs/promises'
import { Navigate, useNavigate } from 'react-router-dom'
import Axios from 'axios'



function Login() {
    const navigate = useNavigate()
    const [email, setEmail] = useState('')
    const [password, setPassword] = useState('')
    const [name, setName] = useState('')


    async function handleSubmit(event: ChangeEvent<HTMLFormElement>) {
        event.preventDefault()

        let obj = {
            name,
            email,
            password,
        }




        // e.preventDefault()
        // let Login = constants.pages.public.login

        if (!name || !email || !password) {
            return alert("Preencha todos os campos antes para realizar o cadastro")
        }


        await Axios.post("http://localhost:3333/user/create", obj)
            .then(response => { 
                console.log(response.data)
            })
        console.log(obj)
        return navigate(`/shops-map`)
    }




    return (
        <main>
            <div className="new-shop">
                <Header
                    path="/"
                    icon={<AiOutlineArrowLeft className='icon' />}
                />

                <Container>
                    <div className="col-12">
                        <form onSubmit={handleSubmit} className='form'>
                            <div className="row">
                                <div className="col-12">
                                    <fieldset>
                                        <legend>Cadastro</legend>

                                        <Input
                                            type="text"
                                            label="Nome"
                                            id='name'
                                            value={name}
                                            onChange={(event) => setName(event.target.value)}
                                        />

                                        <Input
                                            type="text"
                                            label="Email"
                                            id='email'
                                            value={email}
                                            onChange={(event) => setEmail(event.target.value)}
                                        />

                                        <Input
                                            type="password"
                                            label="Senha"
                                            id='password'
                                            value={password}
                                            onChange={(event) => setPassword(event.target.value)}
                                        />

                                    </fieldset>
                                </div>
                            </div>

                            <div className="row">
                                <div className="col-12">
                                    <fieldset>


                                        <Button
                                            type='submit'
                                            className='button'
                                            name='Realizar cadastro'
                                            onClick={() => handleSubmit}
                                        />


                                    </fieldset>


                                </div>
                            </div>
                        </form>
                    </div>
                </Container>
            </div>
        </main>
    )
}


export { Login }