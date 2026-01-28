import React from 'react';
import { useForm } from 'react-hook-form';
import axios from 'axios';

const styles = {
    container: {
        width: '100%',            // Ocupa el 100% de la pantalla
        minHeight: '100vh',       // Asegura que llegue hasta abajo
        margin: '0',              // Quita los márgenes externos
        padding: '20px',          // Un poco de aire para que el texto no pegue al borde
        fontFamily: 'Arial, sans-serif',
        backgroundColor: '#ffffff',
        color: '#000000',
        boxSizing: 'border-box'   // Evita que el padding rompa el ancho en celulares
    },
    header: {
        textAlign: 'center',
        color: '#2c3e50',
        marginBottom: '30px',
        marginTop: '10px'
    },
    section: {
        marginBottom: '20px',
        padding: '20px',          // Más espacio interno
        backgroundColor: '#f8f9fa',
        borderRadius: '8px',      // Bordes un poco más redondeados
        border: '1px solid #e9ecef',
        color: '#000000',
        maxWidth: '1000px',       // OJO: Esto evita que en pantallas gigantes se vea "estirado" feo
        margin: '0 auto'          // Centra el contenido dentro de la pantalla completa
    },
    label: {
        display: 'block',
        marginBottom: '8px',
        fontWeight: 'bold',
        color: '#34495e'
    },
    input: {
        width: '100%',
        padding: '12px',          // Inputs un poco más grandes para tocar fácil en celular
        marginBottom: '15px',
        borderRadius: '6px',
        border: '1px solid #ccc',
        boxSizing: 'border-box',
        backgroundColor: '#ffffff',
        color: '#000000',
        fontSize: '16px'
    },
    textarea: {
        width: '100%',
        padding: '12px',
        height: '100px',
        borderRadius: '6px',
        border: '1px solid #ccc',
        marginBottom: '15px',
        resize: 'vertical',
        boxSizing: 'border-box',
        backgroundColor: '#ffffff',
        color: '#000000',
        fontSize: '16px',
        fontFamily: 'Arial, sans-serif'
    },
    button: {
        width: '100%',
        padding: '18px',          // Botón más grande para dedo
        backgroundColor: '#27ae60',
        color: 'white',
        border: 'none',
        borderRadius: '8px',
        cursor: 'pointer',
        fontSize: '20px',
        fontWeight: 'bold',
        marginTop: '20px',
        transition: 'background 0.3s'
    },
    checkboxContainer: {
        border: '1px solid #ccc',
        borderRadius: '8px',
        padding: '15px',
        marginTop: '10px',
        backgroundColor: '#fff',
        display: 'grid',
        // Esto hace la magia: crea 2 columnas en PC y 1 en celular automáticamente
        gridTemplateColumns: 'repeat(auto-fit, minmax(200px, 1fr))',
        gap: '10px'
    },
    checkboxItem: {
        display: 'flex',
        alignItems: 'center',
        gap: '8px',
        fontSize: '15px',
        color: '#333',
        cursor: 'pointer'
    },
    selectInput: {
        width: '100%',
        padding: '12px',
        borderRadius: '6px',
        border: '1px solid #27ae60', // El borde verde que te gusta
        backgroundColor: '#f9fff9', // Un fondo verdecito muy suave
        fontSize: '16px',
        marginTop: '5px',
        marginBottom: '15px',
        cursor: 'pointer'
    },
    sliderContainer: {
        border: '1px solid #ccc',
        borderRadius: '50px', // Bordes muy redondos (estilo cápsula)
        padding: '15px 25px',
        backgroundColor: '#fff',
        display: 'flex',
        alignItems: 'center',
        gap: '15px',
        marginTop: '10px'
    },
    rangeInput: {
        width: '100%',
        cursor: 'pointer',
        accentColor: '#27ae60' // Esto pinta la bolita y la barra de verde automáticamente
    },
    numberDisplay: {
        fontSize: '24px',
        fontWeight: 'bold',
        color: '#2c3e50',
        minWidth: '30px',
        textAlign: 'center'
    }
};

function App() {
    const { register, handleSubmit,watch } = useForm();
    const valorEstres = watch("nivelEstres", 5);
    const onSubmit = async (data) => {
        try {
            // Enviamos los datos al servidor Java
            const response = await axios.post('http://localhost:8080/api/pdf/generar', data, {
                responseType: 'blob', // Importante: esperamos un archivo binario (PDF)
            });

            // Crear una URL temporal para descargar el archivo
            const url = window.URL.createObjectURL(new Blob([response.data]));
            const link = document.createElement('a');
            link.href = url;
            // Este será el nombre del archivo descargado
            link.setAttribute('download', `Historia_${data.nombrePaciente || 'Paciente'}.pdf`);
            document.body.appendChild(link);
            link.click(); // Forzar el clic de descarga

            // Limpieza
            link.parentNode.removeChild(link);
            alert("¡PDF generado correctamente! Revisa tu carpeta de descargas.");

        } catch (error) {
            console.error("Error completo:", error);
            alert("Error al conectar con el servidor. Asegúrate de que el Backend (Java) esté corriendo en el puerto 8080.");
        }
    };

    return (
        <div style={styles.container}>
            <h1 style={styles.header}>Fisioterapia - Historia Clínica</h1>

            <form onSubmit={handleSubmit(onSubmit)}>

                {/* SECCIÓN 1: DATOS PERSONALES */}
                <div style={styles.section}>
                    <h3>👤 Datos del Paciente</h3>

                    <label style={styles.label}>Nombre Completo:</label>
                    <input
                        style={styles.input}
                        {...register("nombrePaciente", { required: true })}
                        placeholder="Ej: María González"
                    />

                    <label style={styles.label}>Teléfono:</label>
                    <input
                        style={styles.input}
                        {...register("telefono")}
                        placeholder="Ej: 33 1234 5678"
                    />
                </div>

                {/* --- SECCIÓN: ANTECEDENTES (Checkboxes estilo caja) --- */}
                <div style={styles.section}>
                    <h3 style={{color: '#00a8cc'}}>Antecedentes Médicos Patológicos</h3>
                    <label style={styles.label}>Condiciones Crónicas / Cirugías / Traumatismos:</label>



                    <div style={styles.checkboxContainer}>
                        {/* Nota: Todos usan register("antecedentes") para guardarse en la misma lista */}
                        <label style={styles.checkboxItem}>
                            <input type="checkbox" value="Diabetes Mellitus" {...register("antecedentes")} />
                            Diabetes Mellitus
                        </label>

                        <label style={styles.checkboxItem}>
                            <input type="checkbox" value="Hipertensión Arterial" {...register("antecedentes")} />
                            Hipertensión Arterial
                        </label>

                        <label style={styles.checkboxItem}>
                            <input type="checkbox" value="Cirugías Previas" {...register("antecedentes")} />
                            Cirugías Previas
                        </label>

                        <label style={styles.checkboxItem}>
                            <input type="checkbox" value="Fracturas / Luxaciones" {...register("antecedentes")} />
                            Fracturas / Luxaciones
                        </label>

                        <label style={styles.checkboxItem}>
                            <input type="checkbox" value="Enfermedad Reumática" {...register("antecedentes")} />
                            Enfermedad Reumática
                        </label>

                        <label style={styles.checkboxItem}>
                            <input type="checkbox" value="Alergias Conocidas" {...register("antecedentes")} />
                            Alergias Conocidas
                        </label>

                        <label style={styles.checkboxItem}>
                            <input type="checkbox" value="Ninguno" {...register("antecedentes")} />
                            Ninguno
                        </label>
                    </div>
                </div>

                {/* --- SECCIÓN: ESTILO DE VIDA (Dropdown Verde) --- */}
                <div style={styles.section}>
                    <h3 style={{color: '#00a8cc'}}>Estilo de Vida</h3>

                    <label style={styles.label}>Nivel de Actividad Física:</label>

                    {/* El elemento <select> crea el menú desplegable */}
                    <select style={styles.selectInput} {...register("actividadFisica")}>
                        <option value="">-- Seleccione una opción --</option>
                        <option value="Sedentario">Sedentario</option>
                        <option value="Leve (Caminatas)">Leve (Caminatas)</option>
                        <option value="Moderada (3-5 veces/sem)">Moderada (3-5 veces/sem)</option>
                        <option value="Atleta (Intensa, diaria)">Atleta (Intensa, diaria)</option>
                    </select>

                    {/*Sección malos habitos*/}
                    <div style={styles.section}>
                        <label style={styles.label}>Hábitos tóxicos:</label>
                        <div style={styles.checkboxContainer}>

                            <label style={styles.checkboxItem}>
                                <input type="checkbox" value="Fumador/a" {...register("habitosToxicos")} />
                                Fumar
                            </label>

                            <label style={styles.checkboxItem}>
                                <input type="checkbox" value="Alcohol" {...register("habitosToxicos")} />
                                Alcohol
                            </label>

                            <label style={styles.checkboxItem}>
                                <input type="checkbox" value="Drogas" {...register("habitosToxicos")} />
                                Drogas
                            </label>
                        </div>
                        <label style={styles.label}>Horas de sueño:</label>
                        <textarea
                            style={styles.textarea}
                            {...register("horasSueño")}
                            placeholder="Aproximadamente."
                        />


                    {/* --- SECCIÓN: ESTRÉS (Slider) --- */}
                        <label style={styles.label}>Nivel de Estrés Percibido (1-10):</label>

                        <div style={styles.sliderContainer}>
                            {/* El input tipo "range" es la barra deslizante */}
                            <input
                                type="range"
                                min="1"
                                max="10"
                                step="1"
                                style={styles.rangeInput}
                                {...register("nivelEstres")}
                                defaultValue="5" // Valor inicial visual
                            />

                            {/* Aquí mostramos el valor que 'watch' está viendo en tiempo real */}
                            <span style={styles.numberDisplay}>{valorEstres}</span>
                        </div>
                    </div>
                </div>

                {/*EVA*/}
                <div style={styles.section}>
                    <h3 style={{color: '#00a8cc'}}>Evaluación Subjetiva del Dolor (EVA)</h3>
                    <label style={styles.label}>Localización del Dolor: </label>
                    <textarea
                        style={styles.textarea}
                        {...register("localizacionDolor")}
                        placeholder="Region Lumbar..."
                    />
                    <label style={styles.label}>Patron de Irradiación: </label>
                    <textarea style={styles.textarea}{...register("patronIrradiacion")} placeholder={"Ej. Ciatia a pierna izquierda..."}/>
                </div>

                {/* SECCIÓN 2: CONSULTA MÉDICA */}
                <div style={styles.section}>
                    <h3>📋 Detalle de la Consulta</h3>

                    <label style={styles.label}>Motivo de Consulta:</label>
                    <textarea
                        style={styles.textarea}
                        {...register("motivoConsulta")}
                        placeholder="Describe qué le duele, desde cuándo, tipo de dolor..."
                    />

                    <label style={styles.label}>Diagnóstico Fisioterapéutico:</label>
                    <textarea
                        style={styles.textarea}
                        {...register("diagnostico")}
                        placeholder="Conclusión profesional..."
                    />

                    <label style={styles.label}>Plan de Tratamiento:</label>
                    <textarea
                        style={styles.textarea}
                        {...register("tratamiento")}
                        placeholder="Ej: 10 sesiones de ultrasonido, ejercicios de fortalecimiento..."
                    />
                </div>

                <button type="submit" style={styles.button}>
                    🖨️ Generar PDF e Imprimir
                </button>

            </form>
        </div>
    );
}

export default App;