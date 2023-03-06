import { InputText } from 'primereact/inputtext';
import { Button } from 'primereact/button';
import { useForm } from 'react-hook-form';

export const LoginForm = ({ title, onSubmit, onGoToRegister }) => {
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm();

  return (
    <form onSubmit={handleSubmit(onSubmit)}>
      <div className="flex align-items-center justify-content-center">
        <div className="surface-card p-4 shadow-2 border-round w-full lg:w-6">
          <div className="text-center mb-5">
            <div className="text-900 text-3xl font-medium mb-3">{title}</div>
            <span className="text-600 font-medium line-height-3">
              Don't have an account?
            </span>
            <a
              onClick={onGoToRegister}
              className="font-medium no-underline ml-2 text-blue-500 cursor-pointer">
              Create today!
            </a>
          </div>

          <div>
            <label htmlFor="email" className="block text-900 font-medium mb-2">
              Email
            </label>
            <InputText
              id="email"
              type="text"
              placeholder="Email address"
              className="w-full mb-3"
              {...register('email', { required: true })}
            />
            {errors.email && (
              <p className="text-red-300 -mt-2">An email is required</p>
            )}

            <label
              htmlFor="password"
              className="block text-900 font-medium mb-2">
              Password
            </label>
            <InputText
              id="password"
              type="password"
              placeholder="Password"
              className="w-full mb-3"
              {...register('password', { required: true })}
            />
            {errors.password && (
              <p className="text-red-300 -mt-2">A password is required</p>
            )}

            <Button
              label="Sign In"
              type="submit"
              icon="pi pi-user"
              className="w-full"
            />
          </div>
        </div>
      </div>
    </form>
  );
};
