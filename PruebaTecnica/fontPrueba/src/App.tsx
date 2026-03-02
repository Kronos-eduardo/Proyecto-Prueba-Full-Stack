
import React, { useState } from 'react';
import { FaUser, FaEnvelope, FaVenusMars, FaBirthdayCake, FaFutbol, FaBook, FaMusic, FaPlane, FaFilm, FaPalette, FaCheckCircle } from 'react-icons/fa';

const initialForm = {
  nombre: '',
  fechaNacimiento: '',
  correoElectronico: '',
  sexo: '',
  hobbies: [] as string[],
};

function App() {
  const [form, setForm] = useState(initialForm);
  const [errors, setErrors] = useState<any>({});
  const [loading, setLoading] = useState(false);
  const [success, setSuccess] = useState('');
  const [registros, setRegistros] = useState<any[]>([]);

  const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
    const { name, value } = e.target;
    if (name === 'hobbies') {
      let hobbies = [...form.hobbies];
      let checked = false;
      if (e.target instanceof HTMLInputElement) {
        checked = e.target.checked;
      }
      if (checked) {
        if (hobbies.length < 4) hobbies.push(value);
      } else {
        hobbies = hobbies.filter(h => h !== value);
      }
      setForm({ ...form, hobbies });
    } else {
      setForm({ ...form, [name]: value });
    }
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    const newErrors: any = {};
    if (!form.nombre) newErrors.nombre = 'El nombre es obligatorio';
    if (!form.fechaNacimiento) newErrors.fechaNacimiento = 'La fecha es obligatoria';
    if (!form.correoElectronico) newErrors.correoElectronico = 'El email es obligatorio';
    if (!form.sexo) newErrors.sexo = 'El sexo es obligatorio';
    if (form.hobbies.length === 0) newErrors.hobbies = 'Selecciona al menos un hobby';
    setErrors(newErrors);
    if (Object.keys(newErrors).length > 0) return;

    setLoading(true);
    try {
      const response = await fetch('http://localhost:8889/api/registros', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(form),
      });

      if (!response.ok) {
        const errorData = await response.json();
        setErrors({ submit: errorData.message || 'Error al registrar' });
        setLoading(false);
        return;
      }

      const data = await response.json();
      setSuccess('¡Registro exitoso!');
      setForm(initialForm);
      // Recargar registros
      fetchRegistros();
      setTimeout(() => setSuccess(''), 3000);
    } catch (error: any) {
      setErrors({ submit: error.message || 'Error al conectar con el servidor' });
    } finally {
      setLoading(false);
    }
  };

  const fetchRegistros = async () => {
    try {
      const response = await fetch('http://localhost:8889/api/registros');
      if (response.ok) {
        const data = await response.json();
        setRegistros(data.data || []);
      }
    } catch (error) {
      console.error('Error al obtener registros:', error);
    }
  };

  React.useEffect(() => {
    fetchRegistros();
  }, []);

return (
    <div className="container py-5">
      <div className="row justify-content-center">
        <div className="col-lg-8 col-md-10">
          <div className="bg-light p-4 rounded shadow-sm mb-5">
            <h1 className="mb-4 text-center">
              <FaUser className="me-2 text-primary" />Registro de Usuario
            </h1>
            <form onSubmit={handleSubmit} className="row g-3">
              <div className="col-md-6">
                <label className="form-label"><FaUser className="me-2" />Nombre:</label>
                <input name="nombre" value={form.nombre} onChange={handleChange} maxLength={100} className={`form-control ${errors.nombre ? 'is-invalid' : ''}`} />
                {errors.nombre && <div className="invalid-feedback">{errors.nombre}</div>}
              </div>
              <div className="col-md-6">
                <label className="form-label"><FaBirthdayCake className="me-2" />Fecha de nacimiento:</label>
                <input name="fechaNacimiento" value={form.fechaNacimiento} onChange={handleChange} placeholder="dd/mm/yyyy" className={`form-control ${errors.fechaNacimiento ? 'is-invalid' : ''}`} />
                {errors.fechaNacimiento && <div className="invalid-feedback">{errors.fechaNacimiento}</div>}
              </div>
              <div className="col-md-6">
                <label className="form-label"><FaEnvelope className="me-2" />Email:</label>
                <input name="correoElectronico" value={form.correoElectronico} onChange={handleChange} className={`form-control ${errors.correoElectronico ? 'is-invalid' : ''}`} />
                {errors.correoElectronico && <div className="invalid-feedback">{errors.correoElectronico}</div>}
              </div>
              <div className="col-md-6">
                <label className="form-label"><FaVenusMars className="me-2" />Sexo:</label>
                <select name="sexo" value={form.sexo} onChange={handleChange} className={`form-select ${errors.sexo ? 'is-invalid' : ''}`}> 
                  <option value="">Selecciona</option>
                  <option value="M">Masculino</option>
                  <option value="F">Femenino</option>
                </select>
                {errors.sexo && <div className="invalid-feedback">{errors.sexo}</div>}
              </div>
              <div className="col-12">
                <label className="form-label">Hobbies (máx 4):</label>
                <div className="d-flex flex-wrap gap-3">
                  {[
                    { label: 'Deporte', icon: <FaFutbol /> },
                    { label: 'Lectura', icon: <FaBook /> },
                    { label: 'Música', icon: <FaMusic /> },
                    { label: 'Viajar', icon: <FaPlane /> },
                    { label: 'Cine', icon: <FaFilm /> },
                    { label: 'Arte', icon: <FaPalette /> },
                  ].map(hobby => (
                    <div className="form-check" key={hobby.label}>
                      <input
                        type="checkbox"
                        className="form-check-input"
                        name="hobbies"
                        value={hobby.label}
                        checked={form.hobbies.includes(hobby.label)}
                        onChange={handleChange}
                        disabled={
                          !form.hobbies.includes(hobby.label) && form.hobbies.length >= 4
                        }
                        id={`hobby-${hobby.label}`}
                      />
                      <label className="form-check-label d-flex align-items-center" htmlFor={`hobby-${hobby.label}`}>
                        <span className="me-1">{hobby.icon}</span> {hobby.label}
                      </label>
                    </div>
                  ))}
                </div>
                {errors.hobbies && <div className="invalid-feedback d-block">{errors.hobbies}</div>}
              </div>
              <div className="col-12 text-center">
                <button type="submit" disabled={loading} className="btn btn-primary px-5">
                  <FaCheckCircle className="me-2" />Registrar
                </button>
              </div>
              {errors.submit && <div className="alert alert-danger mt-2">{errors.submit}</div>}
              {success && <div className="alert alert-success mt-2">{success}</div>}
            </form>
          </div>
          <div className="bg-white p-4 rounded shadow-sm">
            <h2 className="mb-3 text-center">
              <FaUser className="me-2 text-secondary" />Registros existentes
            </h2>
            <div className="table-responsive">
              <table className="table table-striped table-bordered align-middle">
                <thead className="table-dark">
                  <tr>
                    <th><FaUser /> Nombre</th>
                    <th><FaEnvelope /> Email</th>
                    <th><FaVenusMars /> Sexo</th>
                    <th><FaBirthdayCake /> Fecha Nacimiento</th>
                    <th>Hobbies</th>
                  </tr>
                </thead>
                <tbody>
                  {(Array.isArray(registros) ? registros : []).map(r => (
                    <tr key={r.id}>
                      <td>{r.nombre}</td>
                      <td>{r.correoElectronico}</td>
                      <td>{r.sexo}</td>
                      <td>{r.fechaNacimiento}</td>
                      <td>
                        {r.hobbies && r.hobbies.length > 0 ? (
                          r.hobbies.map((h: string, idx: number) => {
                            const iconMap: any = {
                              'Deporte': <FaFutbol className="me-1" />,
                              'Lectura': <FaBook className="me-1" />,
                              'Música': <FaMusic className="me-1" />,
                              'Viajar': <FaPlane className="me-1" />,
                              'Cine': <FaFilm className="me-1" />,
                              'Arte': <FaPalette className="me-1" />,
                            };
                            return <span key={idx} className="badge bg-info text-dark me-1">{iconMap[h]}{h}</span>;
                          })
                        ) : '-'}
                      </td>
                    </tr>
                  ))}
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default App;
